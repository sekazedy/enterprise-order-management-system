package com.sekazedy.enterpriseordermanagementsystem.event;

public class OrderPaidEvent {
    private final Long orderId;
    private final String userName;
    private final String productName;

    public OrderPaidEvent(Long orderId, String userName, String productName) {
        this.orderId = orderId;
        this.userName = userName;
        this.productName = productName;
    }

    public Long getOrderId() { return orderId; }
    public String getUserName() { return userName; }
    public String getProductName() { return productName; }
}
