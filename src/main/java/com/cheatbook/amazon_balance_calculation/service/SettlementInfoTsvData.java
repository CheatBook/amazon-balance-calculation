package com.cheatbook.amazon_balance_calculation.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.cheatbook.amazon_balance_calculation.deserializer.CustomLocalDateDeserializer;
import com.cheatbook.amazon_balance_calculation.deserializer.CustomLocalDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
public class SettlementInfoTsvData {

  private String settlementId;

  @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
  private LocalDateTime settlementStartDate;
  @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
  private LocalDateTime settlementEndDate;
  @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
  private LocalDateTime depositDate;
  private double totalAmount;
  private String currency;
  private String transactionType;
  private String orderId;
  private String merchantOrderId;
  private String adjustmentId;
  private String shipmentId;
  private String marketplaceName;
  private String amountType;
  private String amountDescription;
  private double amount;
  private String fulfillmentId;
  @JsonDeserialize(using = CustomLocalDateDeserializer.class)
  private LocalDate postedDate;
  @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
  private LocalDateTime postedDateTime;
  private String orderItemCode;
  private String merchantOrderItemId;
  private String merchantAdjustmentItemId;
  private String sku;
  private int quantityPurchased;
  private String promotionId;

}
