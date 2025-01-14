package com.cheatbook.amazon_balance_calculation.service;

import java.time.LocalDate;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AdexpensesRequest {
  private MultipartFile file;
  private LocalDate targetDate;
}
