package com.demo.order_api.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Spring parses the name 'existsBy' + 'Product'
    // It will return true if a record with that product name exists
    boolean existsByProduct(String product);
}