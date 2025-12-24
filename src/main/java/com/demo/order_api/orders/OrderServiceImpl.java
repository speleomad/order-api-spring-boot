package com.demo.order_api.orders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.order_api.orders.dtos.OrderDTO;
import com.demo.order_api.orders.dtos.OrderMapper;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired private OrderRepository repository;
    @Autowired private OrderMapper mapper;

    @Override
    public OrderDTO create(OrderDTO dto) {
        Order entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }


    @Override
    public List<OrderDTO> getAll() {
        return mapper.toDTOList(repository.findAll());
    }

    @Override
    public OrderDTO getById(Long id) {
        Order order = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
        return mapper.toDTO(order);
    }

    @Override
    public OrderDTO updateFull(Long id, OrderDTO dto) {
        
        // Check if exists first
        Order existingOrder = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cannot update: Order not found"));
       
        // Update all fields from the DTO
        existingOrder.setProduct(dto.product());
        existingOrder.setQuantity(dto.quantity());
        existingOrder.setTotalAmount(dto.totalAmount());
        existingOrder.setStatus(dto.status());
        
        return mapper.toDTO(repository.save(existingOrder));
    }

    @Override
    public OrderDTO updateStatus(Long id, String status) {
        Order order = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status); // Modify ONLY the status
        return mapper.toDTO(repository.save(order));
    }

    @Override
    public void delete(Long id) {
        if(!repository.existsById(id)) throw new RuntimeException("Delete failed: ID not found");
        repository.deleteById(id);
    }
}
