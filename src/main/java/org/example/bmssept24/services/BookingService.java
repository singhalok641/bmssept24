package org.example.bmssept24.services;


import org.example.bmssept24.exceptions.ShowNotFoundException;
import org.example.bmssept24.exceptions.UserNotFoundException;
import org.example.bmssept24.models.*;
import org.example.bmssept24.repositories.BookingRepository;
import org.example.bmssept24.repositories.ShowRepository;
import org.example.bmssept24.repositories.ShowSeatRepository;
import org.example.bmssept24.repositories.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private BookingRepository bookingRepository;

    public BookingService(UserRepository userRepository,
                          ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository,
                          BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository = bookingRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking createBooking(Long userId,
                                 List<Long> showSeatids,
                                 Long showId) throws UserNotFoundException, ShowNotFoundException {


        /*
        Solution 2 (optimized) - Homework
        1. Get the user with the given userId
        2. Get the show with the given showId
        3. Get the list of showSeats with the given showSeatIds
        4. Check if all the seats are AVAILABLE
        ------- TAKE A LOCK -----------
        5. Check again if all the seats are AVAILABLE
        6. If not, throw an exception
        7. Else, Mark the status of all the seats as BLOCKED
        8. Save all the changes in the DB as well
        ----------- RELEASE THE LOCK -----------
        9. Create a booking with the pending status [save the booking object in DB]
        10. Return the Booking object
         */

        // Get the user with the given userId
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException("The user with userId:" +
                    userId + " does not exist");
        }

        User user = optionalUser.get();

        // Get the show with the given showId
        Optional<Show> optionalShow = showRepository.findById(showId);

        if(optionalShow.isEmpty()) {
            throw new ShowNotFoundException("The show with showId:" + showId + " does not exist");
        }

        Show show = optionalShow.get();

        // Get all the showSeats with the given showSeatIds
        List< ShowSeat> showSeats = showSeatRepository.findAllById(showSeatids);

        // Check if all the seats are AVAILABLE or NOT
        // If not, throw an exception
        for(ShowSeat showSeat: showSeats) {
            // Check all are available, if not throw exception
        }

        // If all are available, mark all the show seat status as BLOCKED
        for(ShowSeat showSeat: showSeats) {
            showSeat.setStatus(ShowSeatStatus.BLOCKED);
            // Save it to the DB
            showSeatRepository.save(showSeat);
        }

        // Create Booking entity with pending status
        Booking booking = new Booking();
        booking.setStatus(BookingStatus.PENDING);
        booking.setCreatedAt(new Date());
        booking.setUser(user);
        booking.setShow(show);
        booking.setPayments(new ArrayList<>());
        booking.setBookedSeats(showSeats);
        // booking.setAmount(); -- calculate price of the Booking. Price Calculator

        return bookingRepository.save(booking);
    }
}
