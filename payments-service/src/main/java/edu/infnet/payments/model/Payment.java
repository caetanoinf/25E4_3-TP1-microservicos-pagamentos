package edu.infnet.payments.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private Long id;
    private Long orderId;
    private Double amount;
    private String paymentMethod;
    private PaymentStatus status;
    private LocalDateTime processedAt;

    public Payment(Long orderId, Double amount, String paymentMethod) {
        this.orderId = orderId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.processedAt = LocalDateTime.now();
    }
}
