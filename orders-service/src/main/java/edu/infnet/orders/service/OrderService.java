package edu.infnet.orders.service;

import edu.infnet.orders.client.PaymentClient;
import edu.infnet.orders.client.dto.PaymentRequest;
import edu.infnet.orders.client.dto.PaymentResponse;
import edu.infnet.orders.dto.OrderRequest;
import edu.infnet.orders.dto.OrderResponse;
import edu.infnet.orders.model.Order;
import edu.infnet.orders.model.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final AtomicLong orderIdCounter = new AtomicLong(1);

    @Autowired
    private PaymentClient paymentClient;

    public OrderResponse createOrder(OrderRequest request) {
        logger.info("Criando novo pedido para cliente: {}", request.getCustomerName());

        // Cria o pedido
        Order order = new Order(
            request.getCustomerName(),
            request.getProduct(),
            request.getQuantity(),
            request.getTotalAmount()
        );

        // Gera ID do pedido
        order.setId(orderIdCounter.getAndIncrement());
        logger.info("Pedido criado com ID: {}", order.getId());

        // Prepara requisição de pagamento
        PaymentRequest paymentRequest = new PaymentRequest(
            order.getId(),
            order.getTotalAmount(),
            request.getPaymentMethod()
        );

        // Chama o serviço de pagamentos
        logger.info("Processando pagamento para pedido: {}", order.getId());
        PaymentResponse paymentResponse = paymentClient.processPayment(paymentRequest);

        // Atualiza status do pedido baseado no pagamento
        String paymentStatus = paymentResponse.getStatus();
        String message;

        if ("APROVADO".equals(paymentStatus)) {
            order.setStatus(OrderStatus.PAGO);
            message = "Pedido criado e pagamento aprovado com sucesso!";
            logger.info("Pedido {} aprovado", order.getId());
        } else {
            order.setStatus(OrderStatus.CANCELADO);
            message = "Pedido criado mas pagamento foi recusado: " + paymentResponse.getMessage();
            logger.warn("Pedido {} cancelado - pagamento recusado", order.getId());
        }

        // Retorna resposta
        return new OrderResponse(
            order.getId(),
            order.getCustomerName(),
            order.getProduct(),
            order.getTotalAmount(),
            order.getStatus(),
            paymentStatus,
            message
        );
    }
}
