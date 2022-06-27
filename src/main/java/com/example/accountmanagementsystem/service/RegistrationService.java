package com.example.accountmanagementsystem.service;

import com.example.accountmanagementsystem.Dtos.EnterpriseDto;
import com.example.accountmanagementsystem.Dtos.ExpenseCenterDto;
import com.example.accountmanagementsystem.Dtos.LocationDto;
import com.example.accountmanagementsystem.Dtos.OrganizationDto;
import com.example.accountmanagementsystem.entities.LocationDetail;
import com.example.accountmanagementsystem.model.EnterpriseLocationRequest;
import com.example.accountmanagementsystem.model.EnterpriseRequest;
import com.example.accountmanagementsystem.model.LocationRequest;
import com.example.accountmanagementsystem.model.OrganizationRequest;
import java.util.List;

public interface RegistrationService {

  OrganizationDto addOrganization(OrganizationRequest organization);
  EnterpriseDto addEnterpriseToOrganization(EnterpriseRequest enterprise);
  EnterpriseDto addEnterpriseAndLocation(EnterpriseLocationRequest enterpriseLocationRequest);
  List<LocationDetail> addLocationToEnterprise(LocationRequest locationRequest, Integer enterpriseId);
  List<ExpenseCenterDto> addExpenseCenterToLocation(
      List<String> expenseCentername, Integer locationId);
}
