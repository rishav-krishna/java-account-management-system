package com.example.accountmanagementsystem.controller;

import com.example.accountmanagementsystem.Dtos.CustomerCommDto;
import com.example.accountmanagementsystem.Dtos.CustomerDto;
import com.example.accountmanagementsystem.Dtos.CustomerPaymentDto;
import com.example.accountmanagementsystem.Dtos.CustomerPurchasingDto;
import com.example.accountmanagementsystem.entities.BankIfscMaster;
import com.example.accountmanagementsystem.entities.CityMaster;
import com.example.accountmanagementsystem.entities.CreditTerm;
import com.example.accountmanagementsystem.entities.CustomerCommData;
import com.example.accountmanagementsystem.entities.CustomerPaymentData;
import com.example.accountmanagementsystem.entities.CustomerPurchasingData;
import com.example.accountmanagementsystem.entities.DistributionChannel;
import com.example.accountmanagementsystem.model.CustomerCommunicationRequest;
import com.example.accountmanagementsystem.model.CustomerPaymentRequest;
import com.example.accountmanagementsystem.model.CustomerPurchasingRequest;
import com.example.accountmanagementsystem.model.CustomerRequest;
import com.example.accountmanagementsystem.service.CustomerService;
import com.example.accountmanagementsystem.service.UtilService;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

  private final CustomerService customerService;
  private final UtilService utilService;

  public CustomerController(
      final CustomerService customerService,
      final UtilService utilService) {
    this.customerService = customerService;
    this.utilService = utilService;
  }

  @PostMapping("/add-customer")
  public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerRequest customerRequest) {
    return new ResponseEntity<>(customerService.addCustomer(customerRequest), HttpStatus.CREATED);
  }

  @PostMapping("/add-customer-purchasing-detail/{customerId}")
  public ResponseEntity<List<CustomerPurchasingDto>> addCustomerPurchasingData(@PathVariable("customerId") Integer customerId, @RequestBody
                                                                               List<CustomerPurchasingRequest> customerPurchasingRequests) {
    List<CustomerPurchasingData> customerPurchasingData =
        customerService.addCustomerPurchasingData(customerPurchasingRequests, customerId);
    return new ResponseEntity<>(CustomerPurchasingDto.getCustomerPurchasingDto(customerPurchasingData, customerId), HttpStatus.CREATED);
  }

  @PostMapping("/add-customer-payment-detail/{customerId}")
  public ResponseEntity<List<CustomerPaymentDto>> addCustomerPaymentDetail(@PathVariable("customerId") Integer customerId, @RequestBody
      List<CustomerPaymentRequest> customerPaymentRequests) {
    List<CustomerPaymentData> customerPaymentData =
        customerService.addCustomerPaymentDetail(customerPaymentRequests, customerId);
    return new ResponseEntity<>(CustomerPaymentDto.getCustomerPaymentDto(customerPaymentData, customerId), HttpStatus.CREATED);
  }

  @PostMapping("/add-customer-comm-detail/{customerId}")
  public ResponseEntity<List<CustomerCommDto>> addCustomerCommDetail(@PathVariable("customerId") Integer customerId, @RequestBody
      List<CustomerCommunicationRequest> customerCommunicationRequests) {
    List<CustomerCommData> customerCommData =
        customerService.addCustomerCommDetail(customerCommunicationRequests, customerId);
    return new ResponseEntity<>(CustomerCommDto.getCustomerCommDtos(customerCommData, customerId), HttpStatus.CREATED);
  }

  @GetMapping("/get-credit-terms")
  public ResponseEntity<List<CreditTerm>> getAllCreditTerms() {
    return new ResponseEntity<>(utilService.getAllCreditTerms(), HttpStatus.OK);
  }

  @GetMapping("/get-distribution-channels")
  public ResponseEntity<List<DistributionChannel>> getAllDistributionChannels() {
    return new ResponseEntity<>(utilService.getAllDistributionChannel(), HttpStatus.OK);
  }

  @GetMapping("/get-city-detail/{pinCode}")
  public ResponseEntity<CityMaster> getCityDetail(@PathVariable("pinCode") Integer pinCode) {
    Optional<CityMaster> cityDetail = utilService.getCityDetail(pinCode);
    if(cityDetail.isEmpty()){
      throw new RuntimeException("Invalid pinCode");
    }
    return new ResponseEntity<>(cityDetail.get(), HttpStatus.OK);
  }

  @GetMapping("/get-bank-detail/{ifscCode}")
  public ResponseEntity<BankIfscMaster> getBankDetail(@PathVariable("ifscCode") String ifscCode) {
    Optional<BankIfscMaster> bankDetail = utilService.getBankDetail(ifscCode);
    if(bankDetail.isEmpty()) {
      throw new RuntimeException("Invalid IFSC Code");
    }
    return new ResponseEntity<>(bankDetail.get(), HttpStatus.OK);
  }
}
