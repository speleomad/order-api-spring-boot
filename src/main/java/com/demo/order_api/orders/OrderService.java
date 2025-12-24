package com.demo.order_api.orders;
import java.util.List;

import com.demo.order_api.orders.dtos.OrderDTO;

public interface OrderService {
    OrderDTO create(OrderDTO dto);
    List<OrderDTO> getAll();
    OrderDTO getById(Long id);
    OrderDTO updateFull(Long id, OrderDTO dto); // PUT
    OrderDTO updateStatus(Long id, String status); // PATCH
    void delete(Long id);
}