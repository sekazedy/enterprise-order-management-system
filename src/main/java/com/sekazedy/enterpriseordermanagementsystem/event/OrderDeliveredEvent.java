package com.sekazedy.enterpriseordermanagementsystem.event;

public record OrderDeliveredEvent(Long orderId, String userName, String productName) {
}
