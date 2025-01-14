package com.cheatbook.amazon_balance_calculation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cheatbook.amazon_balance_calculation.model.SettlementInfoDetail;

public interface SettlementInfoDetailRepository extends JpaRepository<SettlementInfoDetail, Long> {
}
