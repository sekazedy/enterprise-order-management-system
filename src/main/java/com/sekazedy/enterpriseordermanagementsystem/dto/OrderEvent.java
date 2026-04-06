package com.sekazedy.enterpriseordermanagementsystem.dto;

public class OrderEvent {
    private Long orderId;
    private String username;
    private String productName;

    public OrderEvent(Long orderId, String username, String productName) {
        this.orderId = orderId;
        this.username = username;
        this.productName = productName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
