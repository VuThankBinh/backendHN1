package com.datn.backendHN.repository;

import com.datn.backendHN.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByUserIdOrderByCreatedAtDesc(Integer userId);
    List<Notification> findByUserIdAndIsReadOrderByCreatedAtDesc(Integer userId, Boolean isRead);
    long countByUserIdAndIsRead(Integer userId, Boolean isRead);
} 