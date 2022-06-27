package com.example.accountmanagementsystem.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LocationRequest {

  private List<LocationData> locationData;

  @AllArgsConstructor
  @NoArgsConstructor@Getter@Setter
  public static class LocationData{

    private String locationName;
    private String locationAddress;
  }
}
