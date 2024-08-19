package com.cheatbook.amazon_balance_calculation.model;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

@Data
public class AdexpensesId implements Serializable {
  private static final long serialVersionUID = 1L;
  private LocalDate targetDate;
  private String sku;
}
