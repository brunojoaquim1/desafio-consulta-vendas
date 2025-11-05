package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projection.SallesMinProjection;
import com.devsuperior.dsmeta.projection.SallesReportProjection;

import java.time.LocalDate;

public class SallesReportDTO {
    public Long id;
    public LocalDate date;
    public double amount;
    public String sellerName;


    public SallesReportDTO(Long id, LocalDate date, double amount, String sellerName) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.sellerName = sellerName;
    }

    public SallesReportDTO() {}

    public SallesReportDTO(SallesReportProjection projection){
        id = projection.getID();
        date = projection.getDate();
        amount = projection.getAmount();
        sellerName = projection.getSellerName();
    }

}
