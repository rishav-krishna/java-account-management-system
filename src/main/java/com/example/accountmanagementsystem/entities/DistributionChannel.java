package com.example.accountmanagementsystem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dist_channel_master")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class DistributionChannel {

  @Id
  @Column(name = "dist_channel_code", nullable = false)
  private String distChannelCode;

  @Column(name = "dist_channel_name")
  private String distChannelName;
}
