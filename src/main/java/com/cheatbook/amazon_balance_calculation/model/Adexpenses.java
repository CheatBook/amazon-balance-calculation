package com.cheatbook.amazon_balance_calculation.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Adexpenses")
@IdClass(AdexpensesId.class)
public class Adexpenses {

  @Id
  @Column(name = "target_date", nullable = false)
  private LocalDate targetDate;

  @Id
  @Column(name = "sku", nullable = false)
  private String sku;

  @Column(name = "amount", nullable = false)
  private Double amount;
}
