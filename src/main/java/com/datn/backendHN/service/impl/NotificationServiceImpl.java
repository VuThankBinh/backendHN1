package com.datn.backendHN.service.impl;

import com.datn.backendHN.entity.Notification;
import com.datn.backendHN.dto.request.CreateNotificationRequest;
import com.datn.backendHN.repository.NotificationRepository;
import com.datn.backendHN.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Notification createNotification(CreateNotificationRequest request) {
        Notification notification = new Notification();
        notification.setUserId(request.getUserId());
        notification.setMessage(request.getMessage());
        notification.setTitle(request.getTitle());
        notification.setIsRead(false);
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getNotificationsByUserId(Integer userId) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    @Override
    public List<Notification> getUnreadNotificationsByUserId(Integer userId) {
        return notificationRepository.findByUserIdAndIsReadOrderByCreatedAtDesc(userId, false);
    }

    @Override
    public Notification markAsRead(Integer notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thông báo với id: " + notificationId));
        notification.setIsRead(true);
        return notificationRepository.save(notification);
    }

    @Override
    public void markAllAsRead(Integer userId) {
        List<Notification> unreadNotifications = getUnreadNotificationsByUserId(userId);
        unreadNotifications.forEach(notification -> notification.setIsRead(true));
        notificationRepository.saveAll(unreadNotifications);
    }

    @Override
    public long countUnreadNotifications(Integer userId) {
        return notificationRepository.countByUserIdAndIsRead(userId, false);
    }
} 