package com.example.accountmanagementsystem.controller;

import com.example.accountmanagementsystem.Dtos.OrganizationDto;
import com.example.accountmanagementsystem.Dtos.EnterpriseDto;
import com.example.accountmanagementsystem.Dtos.ExpenseCenterDto;
import com.example.accountmanagementsystem.Dtos.LocationDto;
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
import java.util.Collections;
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
  private final ReaderService readerService;

  public AccountSystemController(
      final RegistrationService registrationService ,
      final ReaderService readerService) {
    this.registrationService = registrationService;
    this.readerService = readerService;
  }

  @GetMapping("/welcome")
  public String getHello() {
    return "Hello!";
  }

  @PostMapping("/add-organization")
  public ResponseEntity<OrganizationDto> addOrganization(@RequestBody OrganizationRequest organizationRequest) {
    return new ResponseEntity<>(registrationService.addOrganization(organizationRequest), HttpStatus.CREATED);
  }

  @GetMapping("/get-organization/{id}")
  public ResponseEntity<OrganizationDto> getOrganization(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(readerService.getOrganization(id));
  }

  @GetMapping("/get-enterprise/{id}")
  public ResponseEntity<EnterpriseDto> getEnterprise(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(readerService.getEnterpriseDto(id));
  }

  @GetMapping("/get-location/{id}")
  public ResponseEntity<LocationDto> getLocation(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(readerService.getLocationDto(id));
  }

  @PostMapping("/add-enterprise")
  public ResponseEntity<EnterpriseDto> addEnterpriseToOrganization(@RequestBody EnterpriseRequest enterprise) {
    return new ResponseEntity<>(
        registrationService.addEnterpriseToOrganization(enterprise),
        HttpStatus.CREATED);
  }

  @PostMapping("/add-enterprise-location")
  public ResponseEntity<EnterpriseDto> addEnterpriseAndLocation(@RequestBody EnterpriseLocationRequest enterpriseLocationRequest) {
    return new ResponseEntity<>(
        registrationService.addEnterpriseAndLocation(enterpriseLocationRequest),
        HttpStatus.CREATED);
  }

  @PostMapping("/add-location/{enterpriseId}")
  public ResponseEntity<List<LocationDto>> addLocationToEnterprise(@RequestBody LocationRequest locationRequest,
                                                                   @PathVariable("enterpriseId") Integer enterpriseId) {
    List<LocationDetail> locationDetails =
        registrationService.addLocationToEnterprise(locationRequest, enterpriseId);
    return new ResponseEntity<>(
        readerService.getLocationDtos(locationDetails),
        HttpStatus.CREATED);
  }

  @PostMapping("/add-expense-center")
  public ResponseEntity<List<ExpenseCenterDto>> addExpenseCenterToLocation(@RequestBody ExpenseCenterRequest expenseCenterRequests) {
    return new ResponseEntity<>(
        registrationService.addExpenseCenterToLocation(expenseCenterRequests.getCenterNames(),
            expenseCenterRequests.getLocationId()) ,
        HttpStatus.CREATED);
  }
}
