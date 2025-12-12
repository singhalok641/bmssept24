package org.example.bmssept24.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Seat extends BaseModel {
    private String seatNumber;
    private SeatType seatType;
    private int rowNumber;
    private int columnNumber;
}
