package com.example.accountmanagementsystem.service;

import com.example.accountmanagementsystem.entities.CityMaster;
import com.example.accountmanagementsystem.entities.CreditTerm;
import com.example.accountmanagementsystem.entities.DistributionChannel;
import java.util.List;
import java.util.Optional;

public interface UtilService {

  List<CreditTerm> getAllCreditTerms();
  List<DistributionChannel> getAllDistributionChannel();
//  List<PaymentTemr> getAllPaymentTerms();
  Optional<CityMaster> getCityDetail(Integer pinCode);
}
