package edu.infnet.payments.dto;

import edu.infnet.payments.model.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private Long orderId;
    private Double amount;
    private PaymentStatus status;
    private String message;
}
