package com.capitol.exam.model.dto;

import java.util.Date;

public class PriceResponseDTO {

    private Long productId;
    private Long brandId;
    private int priceList;
    private Date startDate;
    private Date endDate;
    private double price;

    public PriceResponseDTO(Long productId, Long brandId, int priceList, Date startDate, Date endDate, double price) {
        this.productId = productId;
        this.brandId = brandId;
        this.priceList = priceList;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public int getPriceList() {
        return priceList;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public double getPrice() {
        return price;
    }
}
