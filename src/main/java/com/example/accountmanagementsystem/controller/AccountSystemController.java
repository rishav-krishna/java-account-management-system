package com.example.accountmanagementsystem.controller;

import com.example.accountmanagementsystem.Dtos.OrganizationDto;
import com.example.accountmanagementsystem.entities.EnterpriseDetail;
import com.example.accountmanagementsystem.entities.ExpenseCenterDetail;
import com.example.accountmanagementsystem.entities.LocationDetail;
import com.example.accountmanagementsystem.entities.OrganizationDetail;
import com.example.accountmanagementsystem.model.EnterpriseLocationRequest;
import com.example.accountmanagementsystem.model.EnterpriseRequest;
import com.example.accountmanagementsystem.model.ExpenseCenterRequest;
import com.example.accountmanagementsystem.model.LocationRequest;
import com.example.accountmanagementsystem.model.OrganizationRequest;
import com.example.accountmanagementsystem.service.ReaderService;
import com.example.accountmanagementsystem.service.RegistrationService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AccountSystemController {

  private final RegistrationService registrationService;
  //private final ReaderService readerService;

  public AccountSystemController(
      final RegistrationService registrationService /*,
      final ReaderService readerService*/) {
    this.registrationService = registrationService;
    //this.readerService = readerService;
  }

  @GetMapping("/welcome")
  public String getHello() {
    return "Hello!";
  }

  @PostMapping("/add-organization")
  public ResponseEntity addOrganization(@RequestBody OrganizationRequest organizationRequest) {
    OrganizationDetail organizationDetail = registrationService.addOrganization(organizationRequest);
    return new ResponseEntity(organizationDetail, HttpStatus.CREATED);
  }

//  @GetMapping("/get-organization/{id}")
//  public OrganizationDto getOrganization(@PathVariable("id") Integer id) {
//    return readerService.getOrganization(id);
//  }

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
