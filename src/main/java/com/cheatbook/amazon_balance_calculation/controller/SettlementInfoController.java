package com.cheatbook.amazon_balance_calculation.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.cheatbook.amazon_balance_calculation.model.SettlementInfoSummary;
import com.cheatbook.amazon_balance_calculation.service.MonthlySettlementSummaryService;
import com.cheatbook.amazon_balance_calculation.service.SettlementInfoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SettlementInfoController {

  private final SettlementInfoService settlementInfoService;
  private final MonthlySettlementSummaryService monthlySettlementSummaryService;

  @PostMapping("settlementinfo")
  public ResponseEntity<?> importSettlementInfoData(@RequestBody MultipartFile file) {
    try {
      settlementInfoService.readSettlementInfoDataTsv(file);
      return ResponseEntity.ok("成功"); // 成功時のレスポンス
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                           .body("Error processing file: " + e.getMessage()); // 失敗時のレスポンス
    }
  }

  @GetMapping("settlementinfo")
  public List<SettlementInfoSummary> getTsvSettlementInfoData() {
    return monthlySettlementSummaryService.getMonthlySettlementSummary(LocalDate.of(2024, 6, 1), LocalDate.of(2024, 7, 1));
  }
}
