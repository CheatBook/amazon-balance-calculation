package com.cheatbook.amazon_balance_calculation.service;

import java.time.LocalDate;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AdexpensesRequest {
  private String filePath;
  private LocalDate targetDate;
}
