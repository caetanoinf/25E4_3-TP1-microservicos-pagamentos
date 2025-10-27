package edu.infnet.orders.client;

import edu.infnet.orders.client.dto.PaymentRequest;
import edu.infnet.orders.client.dto.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PaymentClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${payment.service.url}")
    private String paymentServiceUrl;

    public PaymentResponse processPayment(PaymentRequest request) {
        String url = paymentServiceUrl + "/api/payments/process";

        return restTemplate.postForObject(
            url,
            request,
            PaymentResponse.class
        );
    }
}
