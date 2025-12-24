package com.demo.order_api.config;

import com.demo.order_api.orders.OrderService;
import com.demo.order_api.orders.dtos.OrderDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component // Marks this as a bean so Spring runs it automatically
public class DbInit implements CommandLineRunner {

    private final OrderService orderService;
    // Constructor injection is preferred over @Autowired for better testability
    public DbInit(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("--- Starting Database Init ---");

        try {
            // Adding a new product via the Service Layer
            OrderDTO product1 = new OrderDTO(
                null, 
                "MacBook Pro M3", 
                5, 
                12500.00, 
                "PENDING"
            );

            orderService.create(product1);
            System.out.println("Successfully added: " + product1.product());

            // Adding a second product
            OrderDTO product2 = new OrderDTO(
                null, 
                "Dell XPS 15", 
                3, 
                5400.00, 
                "DELIVERED"
            );

            orderService.create(product2);
            System.out.println("Successfully added: " + product2.product());

        } catch (RuntimeException e) {
            // This catches our custom "Product already exists" exception
            System.out.println("Seeding skipped: " + e.getMessage());
        }

        System.out.println("--- Database init Finished ---");
    }
}