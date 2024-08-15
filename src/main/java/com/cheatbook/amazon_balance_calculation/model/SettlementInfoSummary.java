package com.cheatbook.amazon_balance_calculation.model;

import lombok.Data;

@Data
public class SettlementInfoSummary {
  private String sku;
  private String productName;
  private Double amount;
}
