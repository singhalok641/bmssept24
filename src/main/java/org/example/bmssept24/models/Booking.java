package org.example.bmssept24.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
//Ticket Booking made by a User for a Show
public class Booking extends BaseModel {
    private int bookingId;
    private User user;
    private Show show;
    private int amount;
    private List<Payment> payments;
    private Date bookingDate;
    private List<ShowSeat> bookedSeats;
    private BookingStatus status;
}
