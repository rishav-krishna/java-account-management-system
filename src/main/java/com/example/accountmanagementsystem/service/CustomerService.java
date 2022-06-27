package com.example.accountmanagementsystem.service;

import com.example.accountmanagementsystem.Dtos.CustomerDto;
import com.example.accountmanagementsystem.entities.CustomerBasicDetail;
import com.example.accountmanagementsystem.entities.CustomerCommData;
import com.example.accountmanagementsystem.entities.CustomerPaymentData;
import com.example.accountmanagementsystem.entities.CustomerPurchasingData;
import com.example.accountmanagementsystem.model.CustomerCommunicationRequest;
import com.example.accountmanagementsystem.model.CustomerPaymentRequest;
import com.example.accountmanagementsystem.model.CustomerPurchasingRequest;
import com.example.accountmanagementsystem.model.CustomerRequest;
import java.util.List;

public interface CustomerService {

  CustomerDto addCustomer(CustomerRequest customerRequest);
  List<CustomerPurchasingData> addCustomerPurchasingData(List<CustomerPurchasingRequest> customerPurchasingRequests, Integer customerId);
  List<CustomerPaymentData> addCustomerPaymentDetail(List<CustomerPaymentRequest> customerPaymentRequests, Integer customerId);
  List<CustomerCommData> addCustomerCommDetail(List<CustomerCommunicationRequest> customerCommunicationRequests, Integer customerId);
}
