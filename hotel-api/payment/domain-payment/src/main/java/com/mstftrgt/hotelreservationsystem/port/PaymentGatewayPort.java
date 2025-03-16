package com.mstftrgt.hotelreservationsystem.port;

import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import java.util.UUID;
import java.math.BigDecimal;

public interface PaymentGatewayPort {

    UUID pay(BigDecimal amount, CardDetails cardDetails);

    void initiateRefund(UUID transactionId);
}
