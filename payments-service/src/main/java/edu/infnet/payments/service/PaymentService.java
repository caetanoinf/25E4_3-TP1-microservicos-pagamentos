package edu.infnet.payments.service;

import edu.infnet.payments.dto.PaymentRequest;
import edu.infnet.payments.dto.PaymentResponse;
import edu.infnet.payments.model.PaymentStatus;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public PaymentResponse processPayment(PaymentRequest request) {
        PaymentStatus status = request.getAmount() > 1000.0
            ? PaymentStatus.RECUSADO
            : PaymentStatus.APROVADO;

        String message = status == PaymentStatus.APROVADO
            ? "Pagamento aprovado com sucesso"
            : "Pagamento recusado: valor acima do limite permitido";

        return new PaymentResponse(
            request.getOrderId(),
            request.getAmount(),
            status,
            message
        );
    }
}
