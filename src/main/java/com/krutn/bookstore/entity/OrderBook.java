package com.krutn.bookstore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_books")
public class OrderBook {


    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private Integer count;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public OrderBook() {}

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OrderBook{" +
                "order=" + order +
                ", book=" + book +
                ", count=" + count +
                '}';
    }

}
