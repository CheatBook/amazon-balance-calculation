package com.cheatbook.amazon_balance_calculation.model;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "settlement_info")
public class SettlementInfo {

  @Id
  @Column(name = "settlement_id")
  private String settlementId;

  @Column(name = "settlement_start_date")
  private LocalDateTime settlementStartDate;

  @Column(name = "settlement_end_date")
  private LocalDateTime settlementEndDate;

  @Column(name = "deposit_date")
  private LocalDateTime depositDate;

  @Column(name = "total_amount")
  private double totalAmount;

  @Column(name = "currency")
  private String currency;
}
