package com.egadaservices.notification.service;

import com.egadaservices.clients.notification.NotificationRequest;
import com.egadaservices.notification.model.Notification;
import com.egadaservices.notification.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService{
    private final NotificationRepository notificationRepository;

    @Override
    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(
                Notification.builder()
                        .toCustomerId(notificationRequest.toCustomerId())
                        .toCustomerEmail(notificationRequest.toCustomerName())
                        .sender("EgadaServices")
                        .message(notificationRequest.message())
                        .sentAt(LocalDateTime.now())
                        .build()
        );
    }
}
