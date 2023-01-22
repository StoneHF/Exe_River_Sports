package com.example.exeriversports;


abstract class Ticket {
    protected double price;
    public abstract double getPrice();
    public abstract String getTicketInformation();
}

class AdultTicket extends Ticket {
    public AdultTicket() {
        price = 50;
    }
    @Override
    public double getPrice() {
        return price;
    }
    @Override
    public String getTicketInformation() {
        return "Adult Ticket: £" + price;
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
    @Override
    public String getTicketInformation() {
        return "Child Ticket: £" + price;
    }
}

class AdultTicketWithPieAndBeer extends AdultTicket {
    public AdultTicketWithPieAndBeer() {
        price = super.price + 5;
    }
    @Override
    public String getTicketInformation() {
        return "Adult Ticket with Pie and Beer: £" + price;
    }
}

