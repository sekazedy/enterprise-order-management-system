package com.sekazedy.enterpriseordermanagementsystem.event;

public record OrderCancelledEvent(Long orderId, String userName, String productName) {
}
