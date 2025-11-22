package com.randriiv.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.randriiv.amqp", "com.randriiv.notification"})
public class NotificationApplication {
  public static void main(String[] args) {
    SpringApplication.run(NotificationApplication.class, args);
  }

  //  TESTING PUBLISHING MESSAGE TO QUEUE
  //  @Bean
  //  CommandLineRunner commandLineRunner(RabbitMqMessageProducer producer, NotificationConfig
  // config) {
  //    return args ->
  //        producer.publish(
  //            new Person("Roman",29), config.getInternalExchange(),
  // config.getInternalNotificationRoutingKey());
  //  }
  //  record Person(String name, int age) {}
}
