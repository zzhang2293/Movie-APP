package com.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * customer object
 */
public class Customer extends User{
    private List<Movie> tickets;

    public Customer(){
        tickets = new ArrayList<>();

    }

    public Customer(String name, String realName, String gender, String phone, double remain) {
        super(name, realName, gender, phone, remain);
        tickets = new ArrayList<>();
    }

    public List<Movie> getTickets() {
        return tickets;
    }

    public void addTickets(Movie ticket) {
        tickets.add(ticket);
    }
}
