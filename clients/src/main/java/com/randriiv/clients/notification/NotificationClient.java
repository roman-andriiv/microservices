package com.randriiv.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("notification-service")
public interface NotificationClient {
  @PostMapping("api/v1/notification")
  void sentNotification(NotificationRequest notificationRequest);
}
