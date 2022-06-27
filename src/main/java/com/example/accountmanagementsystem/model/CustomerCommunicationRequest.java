package com.example.accountmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class CustomerCommunicationRequest {

  private String communicationType;
  private String communicationData;
}
