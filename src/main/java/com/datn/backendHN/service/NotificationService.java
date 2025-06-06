package com.datn.backendHN.service;

import com.datn.backendHN.entity.Notification;
import com.datn.backendHN.dto.request.CreateNotificationRequest;
import java.util.List;

public interface NotificationService {
    Notification createNotification(CreateNotificationRequest request);
    List<Notification> getNotificationsByUserId(Integer userId);
    List<Notification> getUnreadNotificationsByUserId(Integer userId);
    Notification markAsRead(Integer notificationId);
    void markAllAsRead(Integer userId);
    long countUnreadNotifications(Integer userId);
} 