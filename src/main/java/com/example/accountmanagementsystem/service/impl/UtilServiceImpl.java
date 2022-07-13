package com.example.accountmanagementsystem.service.impl;

import com.example.accountmanagementsystem.entities.BankIfscMaster;
import com.example.accountmanagementsystem.entities.CityMaster;
import com.example.accountmanagementsystem.entities.CreditTerm;
import com.example.accountmanagementsystem.entities.DistributionChannel;
import com.example.accountmanagementsystem.repository.BankIfscRepository;
import com.example.accountmanagementsystem.repository.CityMasterRepository;
import com.example.accountmanagementsystem.repository.CreditTermRepository;
import com.example.accountmanagementsystem.repository.DistChannelRepository;
import com.example.accountmanagementsystem.service.UtilService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UtilServiceImpl implements UtilService {

  private final CreditTermRepository creditTermRepository;
  private final DistChannelRepository distChannelRepository;
  private final CityMasterRepository cityMasterRepository;
  private final BankIfscRepository bankIfscRepository;

  public UtilServiceImpl(
      final BankIfscRepository bankIfscRepository,
      final CreditTermRepository creditTermRepository,
      final DistChannelRepository distChannelRepository,
      final CityMasterRepository cityMasterRepository) {
    this.bankIfscRepository = bankIfscRepository;
    this.creditTermRepository = creditTermRepository;
    this.distChannelRepository = distChannelRepository;
    this.cityMasterRepository = cityMasterRepository;
  }

  @Override
  public List<CreditTerm> getAllCreditTerms() {
    return creditTermRepository.findAll();
  }

  @Override
  public List<DistributionChannel> getAllDistributionChannel() {
    return distChannelRepository.findAll();
  }

  @Override
  public Optional<CityMaster> getCityDetail(Integer pinCode) {
    return cityMasterRepository.findCityMasterByCityPinCode(pinCode);
  }

  @Override
  public Optional<BankIfscMaster> getBankDetail(String ifscCode) {
    return bankIfscRepository.findBankIfscMasterByBranchIfscCode(ifscCode);
  }


}
