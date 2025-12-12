package org.example.bmssept24.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
//Ticket Booking made by a User for a Show
public class Booking extends BaseModel {
    private int bookingId;
    @ManyToOne
    private User user;
    @ManyToOne
    private Show show;
    private int amount;
    @OneToMany
    private List<Payment> payments;
    private Date bookingDate;
    @OneToMany
    private List<ShowSeat> bookedSeats;
    private BookingStatus status;
}
