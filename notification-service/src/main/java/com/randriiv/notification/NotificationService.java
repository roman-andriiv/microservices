package com.randriiv.notification;

import com.randriiv.clients.notification.NotificationRequest;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {
  private final NotificationRepository notificationRepository;

  public void sent(NotificationRequest request) {
    notificationRepository.save(
        Notification.builder()
            .customerId(request.customerId())
            .customerEmail(request.customerEmail())
            .message(request.message())
            .sender("Roman Andriiv")
            .sentAt(LocalDateTime.now())
            .build());
  }
}
