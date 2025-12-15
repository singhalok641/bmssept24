package org.example.bmssept24.repositories;

import org.example.bmssept24.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
