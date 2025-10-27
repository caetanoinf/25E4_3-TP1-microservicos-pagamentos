package edu.infnet.orders.dto;

import edu.infnet.orders.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long orderId;
    private String customerName;
    private String product;
    private Double totalAmount;
    private OrderStatus orderStatus;
    private String paymentStatus;
    private String message;
}
