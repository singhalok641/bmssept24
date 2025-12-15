package org.example.bmssept24.controllers;

import org.example.bmssept24.dtos.CreateBookingRequestDto;
import org.example.bmssept24.dtos.CreateBookingResponseDto;
import org.example.bmssept24.dtos.ResponseStatus;
import org.example.bmssept24.exceptions.ShowNotFoundException;
import org.example.bmssept24.exceptions.UserNotFoundException;
import org.example.bmssept24.models.Booking;
import org.example.bmssept24.services.BookingService;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public CreateBookingResponseDto createBooking(CreateBookingRequestDto requestDto) throws UserNotFoundException, ShowNotFoundException {
        CreateBookingResponseDto responseDto = new CreateBookingResponseDto();

        try {
            // call the booking service to create a booking
            Booking booking = bookingService.createBooking(requestDto.getUserId(),
                    requestDto.getShowSeatIds(),
                    requestDto.getShowId());

            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setBookingId(booking.getId());
        } catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDto;
    }

}

/*

SpringBoot bean <=> Object

class A {
    B b;
    C c
    A(B b, C c) {
     this.b = b;
     this.c = c;
    }
}

class B {
    D d;

}
 */
