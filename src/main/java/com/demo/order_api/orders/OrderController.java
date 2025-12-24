package com.demo.order_api.orders;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.order_api.orders.dtos.OrderDTO;

import jakarta.validation.Valid;

/**
 * REST Controller for managing Market Orders.
 * Provides a RESTful API for CRUD operations and status management.
 * 
 * Endpoint Map:
 * -----------------------------------------------------------------------------------------------
 * Action           | Method | URL Path                    | Parameters / Body    | Success Status
 * -----------------------------------------------------------------------------------------------
 * Create Order     | POST   | /api/v1/orders              | OrderDTO (JSON Body) | 201 Created
 * Get All Orders   | GET    | /api/v1/orders              | None                 | 200 OK
 * Get Single Order | GET    | /api/v1/orders/{id}         | id (Path Variable)   | 200 OK
 * Full Update      | PUT    | /api/v1/orders/{id}         | id + OrderDTO (Body) | 200 OK
 * Update Status    | PATCH  | /api/v1/orders/{id}/status  | id + status (Query)  | 200 OK
 * Delete Order     | DELETE | /api/v1/orders/{id}         | id (Path Variable)   | 204 No Content
 * -----------------------------------------------------------------------------------------------
 *
 * * Base URL: /api/v1/orders
 */
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
   
     private final OrderService orderService;
     // Constructor injection is preferred over @Autowired for better testability
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    // POST: Create a new resource
    @PostMapping
    public ResponseEntity<OrderDTO> create(@Valid @RequestBody OrderDTO dto) {
        return new ResponseEntity<>(orderService.create(dto), HttpStatus.CREATED);
    }

    // GET: Retrieve resources
    @GetMapping
    public List<OrderDTO> getAll() { return orderService.getAll(); }

    @GetMapping("/{id}")
    public OrderDTO getOne(@PathVariable Long id) { return orderService.getById(id); }

    // PUT: Replace/Update the entire resource
    @PutMapping("/{id}")
    public OrderDTO update(@PathVariable Long id, @Valid @RequestBody OrderDTO dto) {
        return orderService.updateFull(id, dto);
    }

    // PATCH: Partial update (Specific to status)
    //http://localhost:8080/api/v1/orders/1/status?status=SHIPPED
    @PatchMapping("/{id}/status")
    public OrderDTO patchStatus(@PathVariable Long id, @RequestParam String status) {
        return orderService.updateStatus(id, status);
    }

    // DELETE: Remove resource
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { orderService.delete(id); }
}