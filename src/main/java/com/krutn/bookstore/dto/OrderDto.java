package com.krutn.bookstore.dto;

import java.util.List;

public class OrderDto {
    private String address;
    private String delivery;
    private String payment;
    private List<Item> items;

    public OrderDto(String address, String delivery, String payment, List<Item> items) {
        this.address = address;
        this.delivery = delivery;
        this.payment = payment;
        this.items = items;
    }


    // Getters and setters
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}