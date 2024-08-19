package com.cheatbook.amazon_balance_calculation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cheatbook.amazon_balance_calculation.service.AdexpensesRequest;
import com.cheatbook.amazon_balance_calculation.service.AdexpensesService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AdexpensesController {

  private final AdexpensesService adexpensesService;

  @PostMapping("adexpenses")
  public ResponseEntity<?> importAdexpensesData(@RequestBody AdexpensesRequest request) {
    try {
      adexpensesService.saveAdexpensesFromExcel(request.getFilePath(), request.getTargetDate());
      return ResponseEntity.ok("成功"); // 成功時のレスポンス
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                           .body("Error processing file: " + e.getMessage()); // 失敗時のレスポンス
    }
  }


}
