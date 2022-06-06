package com.example.accountmanagementsystem.controller;

import com.example.accountmanagementsystem.entities.EnterpriseDetail;
import com.example.accountmanagementsystem.entities.ExpenseCenterDetail;
import com.example.accountmanagementsystem.entities.LocationDetail;
import com.example.accountmanagementsystem.entities.OrganizationDetail;
import com.example.accountmanagementsystem.model.EnterpriseLocationRequest;
import com.example.accountmanagementsystem.model.EnterpriseRequest;
import com.example.accountmanagementsystem.model.ExpenseCenterRequest;
import com.example.accountmanagementsystem.model.LocationRequest;
import com.example.accountmanagementsystem.model.OrganizationRequest;
import com.example.accountmanagementsystem.service.RegistrationService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AccountSystemController {

  private final RegistrationService registrationService;

  public AccountSystemController(
      final RegistrationService registrationService) {
    this.registrationService = registrationService;
  }

  @PostMapping("/add-organization")
  public ResponseEntity addOrganization(@RequestBody OrganizationRequest organizationRequest) {
    OrganizationDetail organizationDetail = registrationService.addOrganization(organizationRequest);
    return new ResponseEntity(organizationDetail, HttpStatus.CREATED);
  }

  @PostMapping("/add-enterprise")
  public ResponseEntity addEnterpriseToOrganization(@RequestBody EnterpriseRequest enterprise) {
    EnterpriseDetail enterpriseDetail =
        registrationService.addEnterpriseToOrganization(enterprise);
    log.info(enterpriseDetail.toString());
    return new ResponseEntity(enterpriseDetail, HttpStatus.CREATED);
  }

  @PostMapping("/add-enterprise-location")
  public ResponseEntity addEnterpriseAndLocation(@RequestBody EnterpriseLocationRequest enterpriseLocationRequest) {
    EnterpriseDetail enterpriseDetail =
        registrationService.addEnterpriseAndLocation(enterpriseLocationRequest);
    return new ResponseEntity(enterpriseDetail, HttpStatus.CREATED);
  }

  @PostMapping("/add-location")
  public ResponseEntity addLocationToEnterprise(@RequestBody LocationRequest locationRequest) {
    LocationDetail locationDetail = registrationService.addLocationToEnterprise(locationRequest);
    return new ResponseEntity(locationDetail, HttpStatus.CREATED);
  }

  @PostMapping("/add-expense-center")
  public ResponseEntity addExpenseCenterToLocation(@RequestBody ExpenseCenterRequest expenseCenterRequests) {
    List<ExpenseCenterDetail> expenseCenterDetails =
        registrationService.addExpenseCenterToLocation(expenseCenterRequests.getCenterNames(),
            expenseCenterRequests.getLocationId());
    return new ResponseEntity(expenseCenterDetails, HttpStatus.CREATED);
  }
}
