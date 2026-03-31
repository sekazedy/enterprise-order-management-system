package com.sekazedy.enterpriseordermanagementsystem.dto;

public class OrderResponse {
    private Long id;
    private String userName;
    private String productName;
    private String status;

    public OrderResponse(Long id, String userName, String productName, String status) {
        this.id = id;
        this.userName = userName;
        this.productName = productName;
        this.status = status;
    }

    public Long getId() { return id; }

    public String getUserName() { return userName; }

    public String getProductName() { return productName; }

    public String getStatus() { return status; }
}
