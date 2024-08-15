package com.cheatbook.amazon_balance_calculation.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cheatbook.amazon_balance_calculation.model.SettlementInfoSummary;
import com.cheatbook.amazon_balance_calculation.service.MonthlySettlementSummaryService;
import com.cheatbook.amazon_balance_calculation.service.SettlementInfoService;
import com.cheatbook.amazon_balance_calculation.service.SettlementInfoTsvData;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SettlementInfoController {

  private final SettlementInfoService settlementInfoService;
  private final MonthlySettlementSummaryService monthlySettlementSummaryService;

  @GetMapping("settlementinfo")
  public List<SettlementInfoTsvData> importTsvSettlementInfoData() {
    return settlementInfoService.readSettlementInfoDataTsv("C:/Users/user/Downloads/59137019949.txt");
  }

  @GetMapping("settlementinfoData")
  public List<SettlementInfoSummary> getTsvSettlementInfoData() {
    return monthlySettlementSummaryService.getMonthlySettlementSummary(LocalDate.of(2024, 7, 1), LocalDate.of(2024, 8, 1));
  }
}
