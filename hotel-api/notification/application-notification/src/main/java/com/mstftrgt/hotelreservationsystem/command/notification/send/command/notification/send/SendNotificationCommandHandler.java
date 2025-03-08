package com.mstftrgt.hotelreservationsystem.command.notification.send.command.notification.send;


import com.mstftrgt.hotelreservationsystem.CommandHandler;
import com.mstftrgt.hotelreservationsystem.facade.ReservationFacade;

import com.mstftrgt.hotelreservationsystem.facade.ReservationInfoContract;
import com.mstftrgt.hotelreservationsystem.notification.port.dto.NotificationSend;
import com.mstftrgt.hotelreservationsystem.notification.port.NotificationSenderPort;
import com.mstftrgt.hotelreservationsystem.notification.repository.NotificationRepository;
import com.mstftrgt.hotelreservationsystem.notification.repository.dto.NotificationCreate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendNotificationCommandHandler implements CommandHandler<SendNotificationCommand> {

    private final ReservationFacade reservationFacade;
    private final NotificationRepository notificationRepository;
    private final NotificationSenderPort notificationSenderPort;
    private final NotificationContentGenerationStrategyFactory notificationContentGenerationStrategyFactory;


    @Override
    public void handle(SendNotificationCommand command) {
        ReservationInfoContract reservationInfo = reservationFacade.findReservationById(command.getReservationId());

        NotificationContentGenerationStrategy strategy =
                notificationContentGenerationStrategyFactory.getStrategy(command.getNotificationType());

        String content = strategy.generateContent(reservationInfo, command.getPaymentAmount());

        notificationRepository.save(buildNotificationCreate(command, content));

        notificationSenderPort.sendNotification(buildNotificationSend(content, reservationInfo));
    }

    private static NotificationSend buildNotificationSend(String content, ReservationInfoContract reservationInfo) {
        return NotificationSend.builder()
                .content(content)
                .phoneNumber(reservationInfo.customerPhoneNumber())
                .emailAddress(reservationInfo.customerEmailAddress())
                .build();
    }


    private static NotificationCreate buildNotificationCreate(SendNotificationCommand command, String content) {
        return new NotificationCreate(command.getReservationId(), content, command.getNotificationType());
    }
}
