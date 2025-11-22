package com.randriiv;

import com.randriiv.amqp.RabbitMqMessageProducer;
import com.randriiv.clients.fraud.FraudCheckResponse;
import com.randriiv.clients.fraud.FraudClient;
import com.randriiv.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final FraudClient fraudClient;
  private final RabbitMqMessageProducer rabbitMqMessageProducer;

  public void registerCustomer(CustomerRegistrationRequest request) {
    Customer customer =
        Customer.builder()
            .firstName(request.firstName())
            .lastName(request.lastName())
            .email(request.email())
            .build();

    // todo: check if email is valid
    // todo: check if email is not taken already

    customerRepository.saveAndFlush(customer);

    FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

    if (fraudCheckResponse.isFraudster()) {
      throw new IllegalStateException("Customer is fraudster");
    }

    NotificationRequest notificationRequest =
        new NotificationRequest(
            customer.getId(),
            customer.getEmail(),
            String.format("Hi %s, its Roman Andriiv", customer.getFirstName()));

    rabbitMqMessageProducer.publish(
        notificationRequest, "internal.exchange", "internal.notification.routing-key");
  }
}
