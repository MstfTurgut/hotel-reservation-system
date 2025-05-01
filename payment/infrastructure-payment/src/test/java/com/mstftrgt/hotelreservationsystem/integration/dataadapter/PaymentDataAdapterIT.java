package com.mstftrgt.hotelreservationsystem.integration.dataadapter;

import com.mstftrgt.hotelreservationsystem.model.Payment;
import com.mstftrgt.hotelreservationsystem.model.PaymentStatus;
import com.mstftrgt.hotelreservationsystem.persistence.PaymentDataAdapter;
import com.mstftrgt.hotelreservationsystem.persistence.entity.PaymentEntity;
import com.mstftrgt.hotelreservationsystem.persistence.repository.PaymentJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Sql(scripts = "classpath:init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@ActiveProfiles("dataAdapterIntegrationTest")
public class PaymentDataAdapterIT {

    @MockitoBean
    private ApplicationEventPublisher publisher;

    private PaymentDataAdapter paymentDataAdapter;

    @Autowired
    private PaymentJpaRepository paymentJpaRepository;

    @BeforeEach
    void setUp() {
        this.paymentDataAdapter = new PaymentDataAdapter(publisher, paymentJpaRepository);
    }

    @Test
    void save_shouldPersistPayment() {
        Payment payment = Payment.builder()
                .id(UUID.randomUUID())
                .transactionId(UUID.randomUUID())
                .reservationId(UUID.randomUUID())
                .amount(BigDecimal.TEN)
                .status(PaymentStatus.COMPLETED)
                .createDate(LocalDateTime.now())
                .build();

        paymentDataAdapter.save(payment);

        Optional<PaymentEntity> result = paymentJpaRepository.findById(payment.getId());

        assertTrue(result.isPresent());
    }

    @Test
    void findById_shouldRetrievePayment() {
        Optional<Payment> optionalPayment = paymentDataAdapter.findById(UUID.fromString("11111111-1111-1111-1111-111111111111"));
        assertTrue(optionalPayment.isPresent());

        Payment payment = optionalPayment.get();
        assertEquals(UUID.fromString("11111111-1111-1111-1111-111111111111"), payment.getId());
        assertEquals(UUID.fromString("11111111-1111-1111-1111-111111111111"), payment.getTransactionId());
        assertEquals(UUID.fromString("11111111-1111-1111-1111-111111111111"), payment.getReservationId());
        assertNotNull(payment.getAmount());
        assertEquals("COMPLETED", payment.getStatus().name());
        assertNotNull(payment.getCreateDate());
    }

    @Test
    void findByReservationId_shouldRetrievePayment() {
        Optional<Payment> optionalPayment = paymentDataAdapter.findByReservationId(UUID.fromString("11111111-1111-1111-1111-111111111111"));
        assertTrue(optionalPayment.isPresent());

        Payment payment = optionalPayment.get();
        assertEquals(UUID.fromString("11111111-1111-1111-1111-111111111111"), payment.getId());
        assertEquals(UUID.fromString("11111111-1111-1111-1111-111111111111"), payment.getTransactionId());
        assertEquals(UUID.fromString("11111111-1111-1111-1111-111111111111"), payment.getReservationId());
        assertNotNull(payment.getAmount());
        assertEquals("COMPLETED", payment.getStatus().name());
        assertNotNull(payment.getCreateDate());
    }
}
