package com.gameshop.Controller;

import com.gameshop.dto.OrderDto;
import com.gameshop.entity.Order;
import com.gameshop.exception.ResourceNotFoundException;
import com.gameshop.repository.OrderRepo;
import com.gameshop.repository.UserRepo;
import com.gameshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    OrderRepo orderRepo;
    UserRepo userRepo;
    OrderService orderService;

    @Autowired
    public OrderController(OrderRepo orderRepo, UserRepo userRepo, OrderService orderService) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.orderService = orderService;
    }

    @GetMapping("/")
    public List<Order> search() {
        return orderRepo.findAll();
    }

    @PostMapping("/")
    public Order createOrder(@Valid @RequestBody Order order) {
        return orderRepo.save(order);
    }

    @PostMapping("/process")
    public String processOrder(@Valid @RequestBody OrderDto orderDto) throws Exception {
        return orderService.processOrder(orderDto);
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable(value = "orderId") Long orderId) {
        return orderRepo.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order", "orderId", orderId));
    }

    @PutMapping("/{orderId}")
    public Order updateOrder(@PathVariable(value = "orderId") Long orderId, @Valid @RequestBody Order orderDeteils) {
        return orderService.updateOrder(orderId, orderDeteils);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable(value = "orderId") Long orderId) {
       return orderService.deleteOrder(orderId);
    }

}
