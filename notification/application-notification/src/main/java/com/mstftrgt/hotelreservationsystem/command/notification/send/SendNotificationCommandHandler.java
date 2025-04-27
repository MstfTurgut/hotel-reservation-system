package com.mstftrgt.hotelreservationsystem.command.notification.send;


import com.mstftrgt.hotelreservationsystem.facade.ReservationFacade;
import com.mstftrgt.hotelreservationsystem.contract.ReservationInfoContract;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandHandler;
import com.mstftrgt.hotelreservationsystem.generic.application.VoidCommandHandler;
import com.mstftrgt.hotelreservationsystem.notification.dto.NotificationSend;
import com.mstftrgt.hotelreservationsystem.notification.model.Notification;
import com.mstftrgt.hotelreservationsystem.notification.port.NotificationSenderPort;
import com.mstftrgt.hotelreservationsystem.notification.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SendNotificationCommandHandler implements VoidCommandHandler<SendNotificationCommand> {

    private final ReservationFacade reservationFacade;
    private final NotificationRepository notificationRepository;
    private final NotificationSenderPort notificationSenderPort;


    @Override
    public void handle(SendNotificationCommand command) {
        ReservationInfoContract reservationInfo = reservationFacade.findReservationById(command.reservationId());

        NotificationContentGenerationStrategy strategy =
                NotificationContentGenerationStrategyFactory.getStrategy(command.notificationType());

        String content = strategy.generateContent(reservationInfo, command.paymentAmount());

        notificationSenderPort.sendNotification(buildNotificationSend(content, reservationInfo));

        Notification notification = Notification.create(command.toNotificationCreateWith(content));

        notificationRepository.save(notification);
    }

    private static NotificationSend buildNotificationSend(String content, ReservationInfoContract reservationInfo) {
        return NotificationSend.builder()
                .content(content)
                .phoneNumber(reservationInfo.customerPhoneNumber())
                .emailAddress(reservationInfo.customerEmailAddress())
                .build();
    }
}
