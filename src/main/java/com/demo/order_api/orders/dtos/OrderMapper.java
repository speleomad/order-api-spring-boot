package com.demo.order_api.orders.dtos;

import java.util.List;

import org.mapstruct.Mapper;

import com.demo.order_api.orders.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    // Maps the Order Entity to an OrderDTO record
    OrderDTO toDTO(Order order);
    
    // Maps the incoming OrderDTO to a new Order Entity
    Order toEntity(OrderDTO orderDTO);
    
    // Maps a list of entities to a list of DTOs for the GET all endpoint
    List<OrderDTO> toDTOList(List<Order> orders);
}