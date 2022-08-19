package com.egadaservices.customer.service;

import com.egadaservices.amqp.RabbitMQMessageProducer;
import com.egadaservices.clients.fraud.FraudCheckResponse;
import com.egadaservices.clients.fraud.FraudClient;
import com.egadaservices.clients.notification.NotificationRequest;
import com.egadaservices.customer.model.Customer;
import com.egadaservices.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    FraudClient fraudClient;

    @Autowired
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    @Override
    public Customer createCustomer(Customer customer) {
        Customer customerResponse = customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Fraudster");
        }

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to Egada Services...", customer.getFirstName())
        );

        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );

        return customerResponse;
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
}
