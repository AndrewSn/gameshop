package com.gameshop.Controller;

import com.gameshop.dto.OrderDto;
import com.gameshop.entity.Order;
import com.gameshop.entity.UserInfo;
import com.gameshop.exception.ResourceNotFoundException;
import com.gameshop.repository.OrderRepo;
import com.gameshop.repository.UserRepo;
import com.gameshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
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

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable(value = "id") Long id) {
        return orderRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable(value = "id") Long id, @Valid @RequestBody Order orderDeteils) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        order.setUserInfo(orderDeteils.getUserInfo());
        order.setStatusPay(orderDeteils.getStatusPay());
        order.setStatusOrder(orderDeteils.getStatusOrder());
        order.setPaymentMethod(orderDeteils.getPaymentMethod());
        order.setTotalAmount(orderDeteils.getTotalAmount());
        order.setCreateOrder(orderDeteils.getCreateOrder());
        order.setSaleAmount(orderDeteils.getSaleAmount());

        return orderRepo.save(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable(value = "id") Long id) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        orderRepo.delete(order);
        return ResponseEntity.ok().build();
    }

}
