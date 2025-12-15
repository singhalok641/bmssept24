package org.example.bmssept24.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateBookingRequestDto {
    private Long userId;
    private Long showId; // can be skipped as we can extract this from the showSeatids
    private List<Long> showSeatIds;
}
