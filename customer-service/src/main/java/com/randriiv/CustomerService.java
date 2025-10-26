package com.randriiv;

import com.andriiv.clients.fraud.FraudCheckResponse;
import com.andriiv.clients.fraud.FraudClient;
import com.andriiv.clients.notification.NotificationClient;
import com.andriiv.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final FraudClient fraudClient;
  private final NotificationClient notificationClient;

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

    notificationClient.sentNotification(
        new NotificationRequest(
            customer.getId(),
            customer.getEmail(),
            String.format("Hi %s, its Roman Andriiv", customer.getFirstName())));
  }
}
