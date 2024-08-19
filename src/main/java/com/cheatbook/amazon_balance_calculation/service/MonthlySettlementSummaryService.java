package com.cheatbook.amazon_balance_calculation.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import com.cheatbook.amazon_balance_calculation.model.SettlementInfoSummary;
import com.cheatbook.amazon_balance_calculation.repository.AdexpensesRepository;
import com.cheatbook.amazon_balance_calculation.repository.MonthlySettlementSummaryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MonthlySettlementSummaryService {
  private final MonthlySettlementSummaryRepository monthlySettlementSummaryRepository;
  private final AdexpensesRepository adexpensesRepository;

  public List<SettlementInfoSummary> getMonthlySettlementSummary(LocalDate startDate, LocalDate endDate) {
    var adexpensesData = adexpensesRepository.findByTargetDate(LocalDate.of(startDate.getYear(), startDate.getMonthValue(), 1));
    var settlementData = monthlySettlementSummaryRepository.findMonthlySettlementSummary(startDate, endDate);
    return settlementData;
  }
}
