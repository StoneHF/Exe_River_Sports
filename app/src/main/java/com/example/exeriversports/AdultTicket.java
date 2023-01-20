package com.example.exeriversports;


// Base Ticket class
abstract class Ticket {
    protected double price;
    public abstract double getPrice();
}

class AdultTicket extends Ticket {
    public AdultTicket() {
        price = 80;
    }
    @Override
    public double getPrice() {
        return price;
    }
}

class ChildTicket extends Ticket {
    public ChildTicket() {
        price = 25;
    }
    @Override
    public double getPrice() {
        return price;
    }
}
