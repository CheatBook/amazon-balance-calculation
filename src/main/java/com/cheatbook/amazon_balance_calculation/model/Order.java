package com.cheatbook.amazon_balance_calculation.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "amazon_order_id", nullable = false, unique = false)
  private String amazonOrderId;

  @Column(name = "merchant_order_id")
  private String merchantOrderId;

  @Column(name = "purchase_date")
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
  private LocalDateTime purchaseDate;

  @Column(name = "last_updated_date")
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
  private LocalDateTime lastUpdatedDate;

  @Column(name = "order_status")
  private String orderStatus;

  @Column(name = "fulfillment_channel")
  private String fulfillmentChannel;

  @Column(name = "sales_channel")
  private String salesChannel;

  @Column(name = "order_channel")
  private String orderChannel;

  @Column(name = "url")
  private String url;

  @Column(name = "ship_service_level")
  private String shipServiceLevel;

  @Column(name = "product_name")
  private String productName;

  @Column(name = "sku")
  private String sku;

  @Column(name = "asin")
  private String asin;

  @Column(name = "item_status")
  private String itemStatus;

  @Column(name = "quantity")
  private String quantity;

  @Column(name = "currency")
  private String currency;

  @Column(name = "item_price")
  private BigDecimal itemPrice;

  @Column(name = "item_tax")
  private BigDecimal itemTax;

  @Column(name = "shipping_price")
  private BigDecimal shippingPrice;

  @Column(name = "shipping_tax")
  private BigDecimal shippingTax;

  @Column(name = "gift_wrap_price")
  private BigDecimal giftWrapPrice;

  @Column(name = "gift_wrap_tax")
  private BigDecimal giftWrapTax;

  @Column(name = "item_promotion_discount")
  private BigDecimal itemPromotionDiscount;

  @Column(name = "ship_promotion_discount")
  private BigDecimal shipPromotionDiscount;

  @Column(name = "ship_city")
  private String shipCity;

  @Column(name = "ship_state")
  private String shipState;

  @Column(name = "ship_postal_code")
  private String shipPostalCode;

  @Column(name = "ship_country")
  private String shipCountry;

  @Column(name = "promotion_ids")
  private String promotionIds;
}
