package com.cheatbook.amazon_balance_calculation.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class SettlementInfoDetailId implements Serializable {

  private static final long serialVersionUID = 1L;
  @Column(name = "settlement_id")
  private String settlementId;

  @Column(name = "order_id")
  private String orderId;

  @Column(name = "amount_description")
  private String amountDescription;
}
