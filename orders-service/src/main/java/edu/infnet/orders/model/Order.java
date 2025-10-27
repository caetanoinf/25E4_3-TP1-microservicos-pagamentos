package edu.infnet.orders.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private String customerName;
    private String product;
    private Integer quantity;
    private Double totalAmount;
    private OrderStatus status;
    private LocalDateTime createdAt;

    public Order(String customerName, String product, Integer quantity, Double totalAmount) {
        this.customerName = customerName;
        this.product = product;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.status = OrderStatus.PENDENTE;
        this.createdAt = LocalDateTime.now();
    }
}
