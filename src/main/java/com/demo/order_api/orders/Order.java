package com.demo.order_api.orders;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;


@NoArgsConstructor // Default constructor: Required by JPA to instantiate the object.
@AllArgsConstructor // Parameterized constructor: For easy object creation with all fields.
@Getter // Generates getter methods for all fields.
@Setter // Generates setter methods for all fields.
@Entity // Specifies that the class is an entity and is mapped to a database table.
@Table(name = "orders") // Explicitly names the database table.
public class Order {

    @Id // Marks this field as the Primary Key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Database handles ID increment (1, 2, 3...).
    private Long id;

   
    // nullable: field cannot be null.
    // length: limits the string size in the DB (e.g., VARCHAR(50)).
    @Column(nullable = false, length = 50) 
    private String product;

    // Standard integer column for item count.
    private int quantity;
    
    // Double precision column for the financial total.
    private double totalAmount;

    // Status: restricted to 20 characters to fit "PENDING", "SHIPPED", etc.
    @Column(length = 20)
    private String status = "PENDING"; 

    
}