package com.cheatbook.amazon_balance_calculation.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.cheatbook.amazon_balance_calculation.model.SettlementInfoSummary;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MonthlySettlementSummaryRepositoryImpl implements MonthlySettlementSummaryRepository {

  private final NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public List<SettlementInfoSummary> findMonthlySettlementSummary(LocalDate startDate, LocalDate endDate) {
    String sql = """
            SELECT
                sid.sku sku,
                pc.product_name productName,
                sid.amount amount
            FROM
                (
                    SELECT
                        sku,
                        SUM(amount) amount
                    FROM
                        settlement_info_detail
                    WHERE
                        transaction_type <> 'other-transaction'
                        AND posted_date >= :startDate
                        AND posted_date < :endDate
                    GROUP BY
                        sku
                ) sid
            INNER JOIN product_category pc
                ON pc.sku = sid.sku
            ORDER BY
                pc.category_id;
        """;

    var params = new MapSqlParameterSource().addValue("startDate", startDate)
                                            .addValue("endDate", endDate);

    return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(SettlementInfoSummary.class));

  }
}
