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

    @Autowired
    OrderRepo orderRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    OrderService orderService;

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
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
        order.setUserInfo(orderDeteils.getUserInfo());
        order.setStatusPay(orderDeteils.getStatusPay());
        order.setStatusOrder(orderDeteils.getStatusOrder());
        order.setPaymentMethod(orderDeteils.getPaymentMethod());
        order.setTotalAmount(orderDeteils.getTotalAmount());
        order.setCreateOrder(orderDeteils.getCreateOrder());
        order.setSaleAmount(orderDeteils.getSaleAmount());
        return orderRepo.save(order);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable(value = "orderId") Long orderId) {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order", "orderId", orderId));
        orderRepo.delete(order);
        return ResponseEntity.ok().build();
    }

}
