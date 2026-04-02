package com.sekazedy.enterpriseordermanagementsystem.event;

public record OrderPaidEvent(Long orderId, String userName, String productName) {
}
