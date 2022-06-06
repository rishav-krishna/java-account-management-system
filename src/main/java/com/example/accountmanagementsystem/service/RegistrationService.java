package com.example.accountmanagementsystem.service;

import com.example.accountmanagementsystem.entities.EnterpriseDetail;
import com.example.accountmanagementsystem.entities.ExpenseCenterDetail;
import com.example.accountmanagementsystem.entities.LocationDetail;
import com.example.accountmanagementsystem.entities.OrganizationDetail;
import com.example.accountmanagementsystem.model.EnterpriseLocationRequest;
import com.example.accountmanagementsystem.model.EnterpriseRequest;
import com.example.accountmanagementsystem.model.ExpenseCenterRequest;
import com.example.accountmanagementsystem.model.LocationRequest;
import com.example.accountmanagementsystem.model.OrganizationRequest;
import java.util.List;

public interface RegistrationService {

  OrganizationDetail addOrganization(OrganizationRequest organization);
  EnterpriseDetail addEnterpriseToOrganization(EnterpriseRequest enterprise);
  EnterpriseDetail addEnterpriseAndLocation(EnterpriseLocationRequest enterpriseLocationRequest);
  LocationDetail addLocationToEnterprise(LocationRequest locationRequest);
  List<ExpenseCenterDetail> addExpenseCenterToLocation(
      List<String> expenseCentername, Integer locationId);
}
