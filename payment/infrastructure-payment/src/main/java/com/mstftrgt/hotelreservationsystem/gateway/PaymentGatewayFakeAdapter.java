package com.mstftrgt.hotelreservationsystem.gateway;

import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import com.mstftrgt.hotelreservationsystem.port.PaymentGatewayPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Service
public class PaymentGatewayFakeAdapter implements PaymentGatewayPort {
    @Override
    public UUID pay(BigDecimal amount, CardDetails cardDetails) {
        log.info("PaymentGatewayAdapter.pay() called with amount: {} and cardDetails: {}", amount, cardDetails);
        return UUID.randomUUID();
    }

    @Override
    public void initiateRefund(UUID transactionId) {
        log.info("PaymentGatewayAdapter.initiateRefund() called with transactionId: {}", transactionId);
    }
}
