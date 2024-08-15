package com.cheatbook.amazon_balance_calculation.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import com.cheatbook.amazon_balance_calculation.model.SettlementInfoSummary;
import com.cheatbook.amazon_balance_calculation.repository.MonthlySettlementSummaryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MonthlySettlementSummaryService {
  private final MonthlySettlementSummaryRepository monthlySettlementSummaryRepository;

  public List<SettlementInfoSummary> getMonthlySettlementSummary(LocalDate startDate, LocalDate endDate) {
    return monthlySettlementSummaryRepository.findMonthlySettlementSummary(startDate, endDate);
  }
}
