package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projection.SallesMinProjection;

public class SallesSumaryDTO {
    public String sellerName;
    public double total;

    public SallesSumaryDTO(String sellerName, double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public SallesSumaryDTO() {}

    public SallesSumaryDTO(SallesMinProjection projection) {
        sellerName = projection.getSellerName();
        total = projection.getTotal();
    }

}
