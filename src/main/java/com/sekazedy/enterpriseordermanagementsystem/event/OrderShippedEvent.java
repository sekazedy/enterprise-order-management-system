package com.sekazedy.enterpriseordermanagementsystem.event;

public record OrderShippedEvent(Long orderId, String userName, String productName) {
}
