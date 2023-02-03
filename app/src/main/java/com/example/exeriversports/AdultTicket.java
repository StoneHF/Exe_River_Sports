package com.example.exeriversports;


//This class defines the blueprint for a Ticket.
abstract class Ticket {
    //Protected member variable price
    protected double price;

    //Abstract method to get the price of the ticket.
    public abstract double getPrice();

    //Abstract method to get the information of the ticket.
    public abstract String getTicketInformation();
}

//This class defines the blueprint for an Adult Ticket.
class AdultTicket extends Ticket {
    //Constructor sets the price of the Adult Ticket.
    public AdultTicket() {
        price = 50;
    }

    //Overridden method to get the price of the Adult Ticket.
    @Override
    public double getPrice() {
        return price;
    }

    //Overridden method to get the information of the Adult Ticket.
    @Override
    public String getTicketInformation() {
        return "Adult Ticket: £" + price;
    }
}

//This class defines the blueprint for a Child Ticket.
class ChildTicket extends Ticket {
    //Constructor sets the price of the Child Ticket.
    public ChildTicket() {
        price = 25;
    }

    //Overridden method to get the price of the Child Ticket.
    @Override
    public double getPrice() {
        return price;
    }

    //Overridden method to get the information of the Child Ticket.
    @Override
    public String getTicketInformation() {
        return "Child Ticket: £" + price;
    }
}

//This class defines the blueprint for an Adult Ticket with pie and beer.
class AdultTicketWithPieAndBeer extends AdultTicket {
    //Constructor sets the price of the Adult Ticket with pie and beer.
    public AdultTicketWithPieAndBeer() {
        price = super.price + 5;
    }

    //Overridden method to get the information of the Adult Ticket with pie and beer.
    @Override
    public String getTicketInformation() {
        return "Adult Ticket with Pie and Beer: £" + price;
    }
}

