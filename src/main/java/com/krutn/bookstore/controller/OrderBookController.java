package com.krutn.bookstore.controller;

import com.krutn.bookstore.entity.OrderBook;
import com.krutn.bookstore.exeption.ResourceNotFoundException;
import com.krutn.bookstore.service.OrderBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-books")
public class OrderBookController {

    @Autowired
    private OrderBookService orderBookService;

    @GetMapping
    public List<OrderBook> getAllOrderBooks() {
        return orderBookService.getAllOrderBooks();
    }

    @GetMapping("/{id}")
    public OrderBook getOrderBookById(@PathVariable Long id) {
        return orderBookService.getOrderBookById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderBook", "id", id));
    }

    @PostMapping
    public OrderBook createOrderBook(@RequestBody OrderBook orderBook) {
        return orderBookService.saveOrderBook(orderBook);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderBook(@PathVariable Long id) {
        orderBookService.deleteOrderBook(id);
    }
}
