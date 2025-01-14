package com.cheatbook.amazon_balance_calculation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.cheatbook.amazon_balance_calculation.service.OrderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PostMapping("orders")
  public ResponseEntity<?> importSettlementInfoData(@RequestBody MultipartFile file) {

    try {
      orderService.saveOrdersFromTsv(file);
      return ResponseEntity.ok("成功"); // 成功時のレスポンス
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                           .body("Error processing file: " + e.getMessage()); // 失敗時のレスポンス
    }
  }

}
