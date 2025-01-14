package com.cheatbook.amazon_balance_calculation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cheatbook.amazon_balance_calculation.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
