package com.lgda.backend.OneOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface OneOrderRepository extends JpaRepository<OneOrder, Long> {


    @Query(value = "SELECT op.product_id, SUM(op.quantity) AS quantite_total " +
            "FROM order_product op " +
            "JOIN one_order o ON op.one_order_id = o.id " +
            "WHERE MONTH(o.created_date) = MONTH(CURRENT_TIMESTAMP) " +
            "AND YEAR(o.created_date) = YEAR(CURRENT_TIMESTAMP) " +
            "GROUP BY op.product_id " +
            "ORDER BY quantite_total DESC " +
            "LIMIT 4", nativeQuery = true)
    List<Object> findTopProductQuantitiesByMonth();

    @Query(value = "SELECT op.product_id, SUM(op.quantity) AS quantite_total " +
            "FROM order_product op " +
            "JOIN one_order o ON op.one_order_id = o.id " +
            "WHERE MONTH(o.created_date) = MONTH(CURRENT_TIMESTAMP) " +
            "AND YEAR(o.created_date) = YEAR(CURRENT_TIMESTAMP) " +
            "GROUP BY op.product_id " +
            "ORDER BY quantite_total ASC " +
            "LIMIT 4", nativeQuery = true)
    List<Object> findWorseProductQuantitiesByMonth();

    @Query(value = "SELECT SUM(o.total_cost) FROM one_order o " +
            "WHERE MONTH(o.created_date) = MONTH(CURRENT_TIMESTAMP) " +
            "AND YEAR(o.created_date) = YEAR(CURRENT_TIMESTAMP)", nativeQuery = true)
    Integer findTotalCostSumByCurrentMonth();

    @Query(value = "SELECT COUNT(o.id) FROM one_order o " +
    "WHERE MONTH(o.created_date) = MONTH(CURRENT_TIMESTAMP) " +
    "AND YEAR(o.created_date) = YEAR(CURRENT_TIMESTAMP)", nativeQuery = true)
    long findCurrentMonthOrderCount();

    @Query(value = "SELECT COUNT(o.id) FROM one_order o " +
            "WHERE WEEK(o.created_date) = WEEK(CURRENT_TIMESTAMP) AND YEAR(o.created_date) = YEAR(CURRENT_TIMESTAMP)", nativeQuery = true)
    long findCurrentWeekOrderCount();

}
