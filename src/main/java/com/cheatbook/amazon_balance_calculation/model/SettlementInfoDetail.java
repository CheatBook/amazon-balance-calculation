package com.cheatbook.amazon_balance_calculation.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "settlement_info_detail")
public class SettlementInfoDetail {

  @EmbeddedId
  private SettlementInfoDetailId id;

  @Column(name = "merchant_order_id")
  private String merchantOrderId;

  @Column(name = "adjustment_id")
  private String adjustmentId;

  @Column(name = "shipment_id")
  private String shipmentId;

  @Column(name = "marketplace_name")
  private String marketplaceName;

  @Column(name = "amount_type")
  private String amountType;

  @Column(name = "amount")
  private double amount;

  @Column(name = "fulfillment_id")
  private String fulfillmentId;

  @Column(name = "posted_date")
  private LocalDate postedDate;

  @Column(name = "posted_date_time")
  private LocalDateTime postedDateTime;

  @Column(name = "order_item_code")
  private String orderItemCode;

  @Column(name = "merchant_order_item_id")
  private String merchantOrderItemId;

  @Column(name = "merchant_adjustment_item_id")
  private String merchantAdjustmentItemId;

  @Column(name = "sku")
  private String sku;

  @Column(name = "quantity_purchased")
  private int quantityPurchased;

  @Column(name = "promotion_id")
  private String promotionId;
}
