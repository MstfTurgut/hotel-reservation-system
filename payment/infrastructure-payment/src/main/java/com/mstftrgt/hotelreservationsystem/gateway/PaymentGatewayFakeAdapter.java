package com.mstftrgt.hotelreservationsystem.gateway;

import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import com.mstftrgt.hotelreservationsystem.port.PaymentGatewayPort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PaymentGatewayFakeAdapter implements PaymentGatewayPort {
    @Override
    public UUID pay(BigDecimal amount, CardDetails cardDetails) {
        System.out.println("PaymentGatewayAdapter.pay() called with amount: " + amount + " and cardDetails: " + cardDetails);
        return UUID.randomUUID();
    }

    @Override
    public void initiateRefund(UUID transactionId) {
        System.out.println("PaymentGatewayAdapter.initiateRefund() called with transactionId: " + transactionId);
    }
}
