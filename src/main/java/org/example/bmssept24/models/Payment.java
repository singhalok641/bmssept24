package org.example.bmssept24.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment extends BaseModel{
    private String referenceNumber;
    private PaymentStatus paymentStatus;
    private double amount;
    private PaymentMode paymentMode;
}
