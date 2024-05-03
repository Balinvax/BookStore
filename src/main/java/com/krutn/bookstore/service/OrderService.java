package com.krutn.bookstore.service;

import com.krutn.bookstore.dto.Item;
import com.krutn.bookstore.dto.OrderDto;
import com.krutn.bookstore.entity.Book;
import com.krutn.bookstore.entity.Order;
import com.krutn.bookstore.entity.OrderBook;
import com.krutn.bookstore.entity.User;
import com.krutn.bookstore.exeption.OrderNotFoundException;
import com.krutn.bookstore.repository.BookRepository;
import com.krutn.bookstore.repository.OrderBookRepository;
import com.krutn.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderBookRepository orderBooksRepository;

    @Transactional
    public void createOrder(OrderDto orderDto) {

        //Отримання ідентифікатора поточного користувача
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userService.findById(userDetails.getId()).orElseThrow();

        Order order = new Order();
        order.setUser(user); // Припустимо, що ми тимчасово використовуємо захардкоджене ID користувача
        order.setDate(new java.sql.Date(System.currentTimeMillis()));
        order.setStatus("pending");
        order.setAddress(orderDto.getAddress());
        order.setDelivery(orderDto.getDelivery());
        order.setPayment(orderDto.getPayment());

         //Розрахунок загальної ціни замовлення
        double totalPrice = orderDto.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        order.setTotalPrice(totalPrice);

        order = orderRepository.save(order); // Збереження замовлення і отримання згенерованого ID

        // Збереження книг замовлення
        for (Item item : orderDto.getItems()) {
            Book book = bookRepository.findByTitle(item.getTitle());
            if (book != null) {
                OrderBook orderBooks = new OrderBook();
                orderBooks.setOrder(order);
                orderBooks.setBook(book);
                orderBooks.setCount(item.getQuantity());
                orderBooksRepository.save(orderBooks);
            }
        }
    }

    public List<Order> getOrderByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId);
        order.setStatus("cancel");
        orderRepository.save(order);
    }


}