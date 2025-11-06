package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.projection.SallesMinProjection;
import com.devsuperior.dsmeta.projection.SallesReportProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(nativeQuery = true , value = "SELECT TB_SELLER.NAME AS sellerName , " +
                                        "SUM(TB_SALES.amount) AS total " +
                                        "FROM TB_SALES " +
                                        "INNER JOIN TB_SELLER ON TB_SELLER.ID =  TB_SALES.seller_id " +
                                        "WHERE DATE BETWEEN :minDate AND :maxDate " +
                                        "AND (:sellerName IS NULL OR TB_SELLER.NAME LIKE CONCAT(:sellerName, '%')) " +
                                        "GROUP BY seller_id ")
    Page<SallesMinProjection> SallesByPeriodOfSeller(
            LocalDate minDate,
            LocalDate maxDate,
            String sellerName,
            Pageable pageable
    );
    @Query(nativeQuery = true , value = "SELECT TB_SALES.ID, " +
                                        "TB_SALES.DATE, " +
                                        "TB_SALES.AMOUNT, " +
                                        "TB_SELLER.NAME AS sellerName " +
                                        "FROM TB_SALES " +
                                        "INNER JOIN TB_SELLER ON TB_SELLER.ID =  TB_SALES.seller_id " +
                                        "WHERE TB_SALES.DATE BETWEEN :minDate AND :maxDate " +
                                        "AND (:sellerName IS NULL OR UPPER(TB_SELLER.NAME) LIKE '%' || UPPER(:sellerName) || '%')")
    List<SallesReportProjection> sallesReport (LocalDate minDate, LocalDate maxDate, String sellerName );


}
