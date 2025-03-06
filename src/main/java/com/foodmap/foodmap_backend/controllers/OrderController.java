package com.foodmap.foodmap_backend.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodmap.foodmap_backend.domain.dto.OrderDto;
import com.foodmap.foodmap_backend.domain.entities.Order;
import com.foodmap.foodmap_backend.domain.entities.OrderStatus;
import com.foodmap.foodmap_backend.mappers.OrderMapper;
import com.foodmap.foodmap_backend.services.OrderService;

@RestController
@CrossOrigin(origins = {"http://localhost:5174", "http://localhost:5173"})
@RequestMapping(path = "/api/order")
public class OrderController {

    
    private final OrderService orderService;

    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper){
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping(path = "/create")
    public OrderDto createOrder(@RequestBody OrderDto orderDto){
        Order order = orderService.createOrder(orderMapper.fromDto(orderDto));
        return orderMapper.toDto(order);
    }

    @GetMapping(path = "/user/{userId}")
    public Order getLastOrder(@PathVariable Long userId){
        return orderService.getOrder(userId);
    }
    
    @GetMapping(path = "/history/{userId}")
    public Page<Order> getOrderHistory(@PathVariable Long userId, @PageableDefault(size = 10, sort = "orderTime", direction = Direction.DESC) Pageable pageable){
        return orderService.listOrders(userId, pageable);
    }

    @GetMapping(path = "/restaurant/{restaurantId}")
    public List<Order> listOrdersByRestaurant(@PathVariable Long restaurantId){
        return orderService.listOrdersByRestaurant(restaurantId);
    }

    @PutMapping(path = "/update-status/{orderId}")
    public Order updateStatus(@PathVariable Long orderId, @RequestBody Map<String, String> requestBody){
        String statusString = requestBody.get("orderStatus");
        OrderStatus orderStatus = OrderStatus.valueOf(statusString.toUpperCase());
        return orderService.updateOrderStatus(orderId.toString(), orderStatus);
    }

    @GetMapping(path = "/{orderId}/order-status")
    public OrderStatus getOrderStatus(@PathVariable Long orderId){
        return orderService.getOrderStatus(orderId);
    }

}
