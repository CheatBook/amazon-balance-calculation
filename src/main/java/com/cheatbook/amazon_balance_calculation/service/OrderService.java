package com.cheatbook.amazon_balance_calculation.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cheatbook.amazon_balance_calculation.model.Order;
import com.cheatbook.amazon_balance_calculation.repository.OrderRepository;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;

  public void saveOrdersFromTsv(MultipartFile file) {
    // Create a CsvMapper and CsvSchema
    CsvMapper csvMapper = new CsvMapper();
    csvMapper.registerModule(new JavaTimeModule());

    // Define the CSV schema for TSV (Tab-Separated Values)
    CsvSchema csvSchema = CsvSchema.builder()
                                   .addColumn("amazonOrderId")
                                   .addColumn("merchantOrderId")
                                   .addColumn("purchaseDate")
                                   .addColumn("lastUpdatedDate")
                                   .addColumn("orderStatus")
                                   .addColumn("fulfillmentChannel")
                                   .addColumn("salesChannel")
                                   .addColumn("orderChannel")
                                   .addColumn("url")
                                   .addColumn("shipServiceLevel")
                                   .addColumn("productName")
                                   .addColumn("sku")
                                   .addColumn("asin")
                                   .addColumn("itemStatus")
                                   .addColumn("quantity")
                                   .addColumn("currency")
                                   .addColumn("itemPrice")
                                   .addColumn("itemTax")
                                   .addColumn("shippingPrice")
                                   .addColumn("shippingTax")
                                   .addColumn("giftWrapPrice")
                                   .addColumn("giftWrapTax")
                                   .addColumn("itemPromotionDiscount")
                                   .addColumn("shipPromotionDiscount")
                                   .addColumn("shipCity")
                                   .addColumn("shipState")
                                   .addColumn("shipPostalCode")
                                   .addColumn("shipCountry")
                                   .addColumn("promotionIds")
                                   .setUseHeader(true)
                                   .setColumnSeparator('\t') // Set separator to tab for TSV
                                   .build();

    // Read TSV file into a List of Order objects
    List<Order> orders = null;
    try (InputStream inputStream = file.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream, Charset.forName("Shift_JIS"))) { // エンコーディングを指定

      orders = csvMapper.readerFor(Order.class)
                        .with(csvSchema)
                        .<Order>readValues(reader) // 型安全な方法で値を読み取る
                        .readAll();

    } catch (IOException e) {
      e.printStackTrace();
    }

    // Save Orders to the database
    if (orders != null) {
      orderRepository.saveAll(orders);
    }
  }
}
