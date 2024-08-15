package com.cheatbook.amazon_balance_calculation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cheatbook.amazon_balance_calculation.model.SettlementInfo;

public interface SettlementInfoRepository extends JpaRepository<SettlementInfo, String> {
}
