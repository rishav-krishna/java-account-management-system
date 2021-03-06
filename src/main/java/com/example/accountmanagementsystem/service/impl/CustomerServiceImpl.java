package com.example.accountmanagementsystem.service.impl;

import static com.example.accountmanagementsystem.constants.ApplicationConstants.CUSTOMER_INCREMENT_VALUE;
import static com.example.accountmanagementsystem.constants.ApplicationConstants.CUSTOMER_START_VALUE;
import static com.example.accountmanagementsystem.constants.Errors.Customer.INVALID_CUSTOMER_ID;

import com.example.accountmanagementsystem.Dtos.CustomerDto;
import com.example.accountmanagementsystem.constants.Errors;
import com.example.accountmanagementsystem.entities.BankIfscMaster;
import com.example.accountmanagementsystem.entities.CreditTerm;
import com.example.accountmanagementsystem.entities.CustomerBasicDetail;
import com.example.accountmanagementsystem.entities.CustomerCommData;
import com.example.accountmanagementsystem.entities.CustomerPaymentData;
import com.example.accountmanagementsystem.entities.CustomerPurchasingData;
import com.example.accountmanagementsystem.entities.DistributionChannel;
import com.example.accountmanagementsystem.entities.OrganizationDetail;
import com.example.accountmanagementsystem.exception.InvalidRequestException;
import com.example.accountmanagementsystem.model.CustomerCommunicationRequest;
import com.example.accountmanagementsystem.model.CustomerPaymentRequest;
import com.example.accountmanagementsystem.model.CustomerPurchasingRequest;
import com.example.accountmanagementsystem.model.CustomerRequest;
import com.example.accountmanagementsystem.repository.BankIfscRepository;
import com.example.accountmanagementsystem.repository.CreditTermRepository;
import com.example.accountmanagementsystem.repository.CustomerCommRepository;
import com.example.accountmanagementsystem.repository.CustomerDetailRepository;
import com.example.accountmanagementsystem.repository.CustomerPaymentRepository;
import com.example.accountmanagementsystem.repository.CustomerPurchasingRepository;
import com.example.accountmanagementsystem.repository.DistChannelRepository;
import com.example.accountmanagementsystem.repository.OrganizationRepository;
import com.example.accountmanagementsystem.service.CustomerService;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerDetailRepository customerDetailRepository;
  private final OrganizationRepository organizationRepository;
  private final CreditTermRepository creditTermRepository;
  private final DistChannelRepository distChannelRepository;
  private final CustomerPurchasingRepository customerPurchasingRepository;
  private final CustomerPaymentRepository customerPaymentRepository;
  private final CustomerCommRepository customerCommRepository;
  private final BankIfscRepository bankIfscRepository;
  private Map<String, CreditTerm> creditTermMap = new ConcurrentHashMap<>();
  private Map<String, DistributionChannel> distChnlMap = new ConcurrentHashMap<>();
  private Map<String, BankIfscMaster> bankIfscMap = new ConcurrentHashMap<>();

  public CustomerServiceImpl(
      final CustomerDetailRepository customerDetailRepository,
      final OrganizationRepository organizationRepository,
      final CreditTermRepository creditTermRepository,
      final DistChannelRepository distChannelRepository,
      final CustomerPurchasingRepository customerPurchasingRepository,
      final CustomerPaymentRepository customerPaymentRepository,
      final CustomerCommRepository customerCommRepository,
      final BankIfscRepository bankIfscRepository) {
    this.customerDetailRepository = customerDetailRepository;
    this.organizationRepository = organizationRepository;
    this.creditTermRepository = creditTermRepository;
    this.distChannelRepository = distChannelRepository;
    this.customerPurchasingRepository = customerPurchasingRepository;
    this.customerPaymentRepository = customerPaymentRepository;
    this.customerCommRepository = customerCommRepository;
    this.bankIfscRepository = bankIfscRepository;
    createCreditTermMap();
    createDistChnlMap();
    createBankIfscMap();
  }

  private void createBankIfscMap() {
    List<BankIfscMaster> bankIfscMasters = bankIfscRepository.findAll();
    bankIfscMap = bankIfscMasters.stream()
        .collect(Collectors.toMap(BankIfscMaster::getBranchIfscCode, Function.identity()));
  }

  private void createDistChnlMap() {
    List<DistributionChannel> distributionChannels = distChannelRepository.findAll();
    distChnlMap = distributionChannels.stream()
        .collect(Collectors.toMap(DistributionChannel::getDistChannelCode, Function.identity()));
  }

  private void createCreditTermMap() {
    List<CreditTerm> creditTerms = creditTermRepository.findAll();
    creditTermMap = creditTerms.stream().collect(Collectors.toMap(CreditTerm::getCreditTermCode, Function.identity()));
  }

  @Override
  @Transactional
  public CustomerDto addCustomer(CustomerRequest customerRequest) {
    Optional<OrganizationDetail> organizationDetail =
        organizationRepository.findById(customerRequest.getOrganizationId());
    if(organizationDetail.isEmpty())
      throw new InvalidRequestException(Errors.Organization.INVALID_ORG_ID);

    Optional<String> customerCodeByOrgId =
        customerDetailRepository.findCustomerCodeByOrgId(customerRequest.getOrganizationId());
    String customerCode = generateCustomerCode(customerCodeByOrgId, organizationDetail.get());
    CustomerBasicDetail customerBasicDetail = customerDetailRepository.save(CustomerBasicDetail.builder()
        .customerCode(customerCode)
        .customerName(customerRequest.getCustomerName())
        .customerAddress(customerRequest.getCustomerAddress())
        .customerPinCode(customerRequest.getPinCode())
        .customerCommData(new HashSet<>())
        .customerPaymentData(new HashSet<>())
        .organizationDetail(organizationDetail.get())
        .build());

    List<CustomerPurchasingData> customerPurchasingData =
        addCustomerPurchasingData(customerRequest.getCustomerPurchasingRequests(),
            customerBasicDetail.getCustomerId());
    List<CustomerPaymentData> customerPaymentData =
        addCustomerPaymentDetail(customerRequest.getCustomerPaymentRequests(),
            customerBasicDetail.getCustomerId());
    customerPaymentData.forEach(customerBasicDetail::addCustomerPaymentData);
    List<CustomerCommData> customerCommData =
        addCustomerCommDetail(customerRequest.getCustomerCommunicationRequests(),
            customerBasicDetail.getCustomerId());
    customerCommData.forEach(customerBasicDetail::addCustomerCommData);
    customerDetailRepository.save(customerBasicDetail);
    return CustomerDto.getCustomerDto(customerBasicDetail, customerPurchasingData, customerPaymentData, customerCommData);
  }

  private String generateCustomerCode(Optional<String> customerCodeByOrgId, OrganizationDetail organizationDetail) {
    Integer startCustomerCode = organizationDetail.getOrgId()*CUSTOMER_START_VALUE;
    return customerCodeByOrgId.map(cc -> {
      Integer lastIncrementValue = Integer.parseInt(cc.substring(2)) + CUSTOMER_INCREMENT_VALUE;
      return "cc"+ lastIncrementValue;
    }).orElse("cc"+startCustomerCode);
  }

  @Override
  public List<CustomerPurchasingData> addCustomerPurchasingData(
      List<CustomerPurchasingRequest> customerPurchasingRequests, Integer customerId) {
    if(customerPurchasingRequests.isEmpty())
      return Collections.emptyList();
    Optional<CustomerBasicDetail> customerBasicDetail = customerDetailRepository.findById(customerId);
    if(customerBasicDetail.isEmpty())
      throw new InvalidRequestException(INVALID_CUSTOMER_ID);
    List<CustomerPurchasingData> customerPurchasingData =
        customerPurchasingRequests.stream().map(c -> customerPurchasingRepository.save(
            CustomerPurchasingData.builder()
                .customerVatTin(c.getCustomerVatTin())
                .customerGstNbr(c.getCustomerGstNbr())
                .customerPanNbr(c.getCustomerPanNbr())
                .customerTanNbr(c.getCustomerTanNbr())
                .customerUdyogAadharNbr(c.getCustomerUdyogAadharNbr())
                .customerCreditTerm(creditTermMap.get(c.getCustomerCreditTermCode()))
                .distributionChannel(distChnlMap.get(c.getDistributionChannelCode()))
                .customerBasicDetail(customerBasicDetail.get())
                .build())).collect(Collectors.toList());
    return customerPurchasingData;
  }

  @Override
  public List<CustomerPaymentData> addCustomerPaymentDetail(
      List<CustomerPaymentRequest> customerPaymentRequests, Integer customerId) {
    if(customerPaymentRequests.isEmpty())
      return Collections.emptyList();
    Optional<CustomerBasicDetail> customerBasicDetail = customerDetailRepository.findById(customerId);
    if(customerBasicDetail.isEmpty())
      throw new InvalidRequestException(INVALID_CUSTOMER_ID);
    List<CustomerPaymentData> customerPaymentDataList =
        customerPaymentRequests.stream().map(c -> customerPaymentRepository.save(
            CustomerPaymentData.builder()
                .customerAccountNbr(c.getCustomerAccountNumber())
                .bankIfscCode(bankIfscMap.get(c.getIfscCode()))
                .accountType(c.getAccountType())
                .accountHolderName(c.getAccountHolderName())
                .customerBasicDetails(new HashSet<>())
                .build())).collect(Collectors.toList());
    customerPaymentDataList.forEach(c -> customerBasicDetail.get().addCustomerPaymentData(c));
    customerDetailRepository.save(customerBasicDetail.get());
    return customerPaymentDataList;
  }

  @Override
  public List<CustomerCommData> addCustomerCommDetail(
      List<CustomerCommunicationRequest> customerCommunicationRequests, Integer customerId) {
    if(customerCommunicationRequests.isEmpty())
      return Collections.emptyList();
    Optional<CustomerBasicDetail> customerBasicDetail = customerDetailRepository.findById(customerId);
    if(customerBasicDetail.isEmpty())
      throw new InvalidRequestException(INVALID_CUSTOMER_ID);
    List<CustomerCommData> customerCommDataList =
        customerCommunicationRequests.stream().map(c -> customerCommRepository.save(
            CustomerCommData.builder()
                .communicationType(c.getCommunicationType())
                .communicationData(c.getCommunicationData())
                .customerBasicDetails(new HashSet<>())
                .build())).collect(Collectors.toList());
    customerCommDataList.forEach(c-> customerBasicDetail.get().addCustomerCommData(c));
    customerDetailRepository.save(customerBasicDetail.get());
    return customerCommDataList;
  }

  @Override
  public List<CustomerDto> getCustomers(Integer orgId) {
    List<CustomerBasicDetail> customerDetails =
        customerDetailRepository.findAllByOrganizationDetail(orgId);
    return customerDetails.stream().map(c -> CustomerDto.builder()
        .customerId(c.getCustomerId())
        .customerName(c.getCustomerName())
        .customerAddress(c.getCustomerAddress())
        .customerCode(c.getCustomerCode())
        .pinCode(c.getCustomerPinCode())
        .build()).collect(Collectors.toList());
  }

  @Override
  public CustomerDto getCustomerById(Integer customerId) {
    Optional<CustomerBasicDetail> customerBasicDetail = customerDetailRepository.findById(customerId);
    if(customerBasicDetail.isEmpty()) {
      throw new InvalidRequestException(INVALID_CUSTOMER_ID);
    }

    final List<CustomerPurchasingData> purchasingDataList =
        customerPurchasingRepository.findByCustomerBasicDetail(customerId);
    List<CustomerPaymentData> customerPaymentDataList =
        customerPaymentRepository.findAllByCustomerBasicDetail(customerId);
    final List<CustomerCommData> customerCommDataList =
        customerCommRepository.findAllByCustomerBasicDetail(customerId);
    return CustomerDto.getCustomerDto(customerBasicDetail.get(), purchasingDataList,customerPaymentDataList, customerCommDataList);
  }
}
