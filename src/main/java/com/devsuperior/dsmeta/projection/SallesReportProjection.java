package com.devsuperior.dsmeta.projection;

import java.time.LocalDate;

public interface SallesReportProjection {
    Long getID();
    LocalDate getDate();
    double getAmount();
    String getSellerName();
}
