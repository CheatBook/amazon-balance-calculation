package com.cheatbook.amazon_balance_calculation.service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cheatbook.amazon_balance_calculation.model.Adexpenses;
import com.cheatbook.amazon_balance_calculation.repository.AdexpensesRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdexpensesService {

  private final AdexpensesRepository adexpensesRepository;

  public void saveAdexpensesFromExcel(MultipartFile file, LocalDate tagetDate) {
    Map<String, Adexpenses> adexpensesMap = new HashMap<>();

    try (InputStream inputStream = file.getInputStream(); Workbook workbook = new XSSFWorkbook(inputStream)) {

      Sheet sheet = workbook.getSheetAt(0);
      boolean firstRow = true;

      for (Row row : sheet) {
        if (firstRow) {
          firstRow = false; // Skip header row
          continue;
        }

        Cell skuCell = row.getCell(6);
        Cell amountCell = row.getCell(12);

        if (skuCell != null && skuCell.getCellType() == CellType.STRING) {
          String sku = skuCell.getStringCellValue();
          Double amount = 0.0;

          if (amountCell != null && amountCell.getCellType() == CellType.NUMERIC) {
            amount = amountCell.getNumericCellValue();
          }

          Adexpenses adexpenses = adexpensesMap.get(sku);
          if (adexpenses == null) {
            adexpenses = new Adexpenses();
            adexpenses.setTargetDate(tagetDate);
            adexpenses.setSku(sku);
            adexpenses.setAmount(amount);
            adexpensesMap.put(sku, adexpenses);
          } else {
            adexpenses.setAmount(adexpenses.getAmount() + amount);
          }
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    adexpensesRepository.saveAll(adexpensesMap.values()
                                              .stream()
                                              .toList());
  }
}
