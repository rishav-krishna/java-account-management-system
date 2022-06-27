package com.example.accountmanagementsystem.service;

import com.example.accountmanagementsystem.Dtos.EnterpriseDto;
import com.example.accountmanagementsystem.Dtos.LocationDto;
import com.example.accountmanagementsystem.Dtos.OrganizationDto;
import com.example.accountmanagementsystem.entities.LocationDetail;
import java.util.List;

public interface ReaderService {

  OrganizationDto getOrganization(Integer organizationId);
  EnterpriseDto getEnterpriseDto(Integer enterpriseId);
  LocationDto getLocationDto(Integer locationId);
  List<LocationDto> getLocationDtos(List<LocationDetail> locationDetails);
}
