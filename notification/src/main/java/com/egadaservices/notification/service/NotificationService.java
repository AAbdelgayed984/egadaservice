package com.egadaservices.notification.service;

import com.egadaservices.clients.notification.NotificationRequest;

public interface NotificationService {
    public void send(NotificationRequest notificationRequest);
}
