package com.cheatbook.amazon_balance_calculation.repository;

import java.time.LocalDate;
import java.util.List;
import com.cheatbook.amazon_balance_calculation.model.SettlementInfoSummary;

public interface MonthlySettlementSummaryRepository {
  public List<SettlementInfoSummary> findMonthlySettlementSummary(LocalDate startDate, LocalDate endDate);
}
