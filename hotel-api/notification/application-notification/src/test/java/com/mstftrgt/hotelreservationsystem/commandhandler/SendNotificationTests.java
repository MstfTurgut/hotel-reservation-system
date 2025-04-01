package com.mstftrgt.hotelreservationsystem.commandhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.command.notification.send.NotificationContentGenerationStrategy;
import com.mstftrgt.hotelreservationsystem.command.notification.send.NotificationContentGenerationStrategyFactory;
import com.mstftrgt.hotelreservationsystem.command.notification.send.SendNotificationCommand;
import com.mstftrgt.hotelreservationsystem.command.notification.send.SendNotificationCommandHandler;
import com.mstftrgt.hotelreservationsystem.exception.ReservationInfoCouldNotBeRetrievedException;
import com.mstftrgt.hotelreservationsystem.facade.ReservationFacade;
import com.mstftrgt.hotelreservationsystem.facade.ReservationInfoContract;
import com.mstftrgt.hotelreservationsystem.notification.dto.NotificationSend;
import com.mstftrgt.hotelreservationsystem.notification.model.Notification;
import com.mstftrgt.hotelreservationsystem.notification.port.NotificationSenderPort;
import com.mstftrgt.hotelreservationsystem.notification.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SendNotificationTests {

    @Mock
    private ReservationFacade reservationFacade;

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private NotificationSenderPort notificationSenderPort;

    @Mock
    private NotificationContentGenerationStrategyFactory notificationContentGenerationStrategyFactory;

    @Mock
    private NotificationContentGenerationStrategy notificationContentGenerationStrategy;

    @Mock
    private ReservationInfoContract reservationInfo;

    @InjectMocks
    private SendNotificationCommandHandler handler;

    @Test
    void shouldSendNotificationSuccessfully() {
        SendNotificationCommand command = ApplicationTestDataFactory.getSendNotificationTestCommand();
        String generatedContent = "Test notification content";

        when(reservationFacade.findReservationById(command.reservationId())).thenReturn(Optional.of(reservationInfo));
        when(notificationContentGenerationStrategyFactory.getStrategy(command.notificationType())).thenReturn(notificationContentGenerationStrategy);
        when(notificationContentGenerationStrategy.generateContent(reservationInfo, command.paymentAmount())).thenReturn(generatedContent);
        when(reservationInfo.customerPhoneNumber()).thenReturn("1234567890");
        when(reservationInfo.customerEmailAddress()).thenReturn("test@example.com");

        handler.handle(command);

        NotificationSend expectedNotificationSend = NotificationSend.builder()
                .content(generatedContent)
                .phoneNumber("1234567890")
                .emailAddress("test@example.com")
                .build();

        verify(notificationSenderPort).sendNotification(expectedNotificationSend);

        ArgumentCaptor<Notification> notificationCaptor = ArgumentCaptor.forClass(Notification.class);
        verify(notificationRepository).save(notificationCaptor.capture());

        Notification savedNotification = notificationCaptor.getValue();
        assertEquals(command.reservationId(), savedNotification.getReservationId());
        assertEquals(generatedContent, savedNotification.getContent());
        assertEquals(command.notificationType(), savedNotification.getType());
        assertNotNull(savedNotification.getId());
        assertNotNull(savedNotification.getCreatedAt());
    }

    @Test
    void shouldThrowIfReservationInfoCouldNotBeRetrieved() {
        SendNotificationCommand command = ApplicationTestDataFactory.getSendNotificationTestCommand();

        when(reservationFacade.findReservationById(command.reservationId())).thenReturn(Optional.empty());

        assertThrows(ReservationInfoCouldNotBeRetrievedException.class, () -> handler.handle(command));

        verify(notificationContentGenerationStrategyFactory, never()).getStrategy(any());
        verify(notificationContentGenerationStrategy, never()).generateContent(any(), any());
        verify(notificationSenderPort, never()).sendNotification(any());
        verify(notificationRepository, never()).save(any());
    }
}