package com.cheatbook.amazon_balance_calculation.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cheatbook.amazon_balance_calculation.model.Adexpenses;
import com.cheatbook.amazon_balance_calculation.model.AdexpensesId;

@Repository
public interface AdexpensesRepository extends JpaRepository<Adexpenses, AdexpensesId> {
  List<Adexpenses> findByTargetDate(LocalDate targetDate);
}
