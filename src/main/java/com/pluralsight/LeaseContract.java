package com.pluralsight;

import java.time.LocalDate;

public class LeaseContract extends Contract {

    public LeaseContract(LocalDate contractDate, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(contractDate, customerName, customerEmail, vehicleSold);
    }

    public double getExpectedEndingValue() {
        double endingValuePercentage = 50;
        return vehicleSold.getPrice() * (endingValuePercentage / 100);
    }

    public double getLeaseFee() {
        double leaseFeePercentage = 7;
        return vehicleSold.getPrice() * (leaseFeePercentage / 100);
    }

    @Override
    public double getTotalPrice() {
        return vehicleSold.getPrice() - getExpectedEndingValue() + getLeaseFee();
    }

    @Override
    public double getMonthlyPayment() {
        double interestAnnual = 4;
        double loanLengthYears = 3;
        double interest = interestAnnual / 100 / 12;
        double loanLengthMonths = loanLengthYears * 12;
        return (vehicleSold.getPrice() - getExpectedEndingValue()) *
                ((interest * Math.pow((1 + interest), loanLengthMonths)) /
                (Math.pow((1 + interest), loanLengthMonths) - 1));
    }

}
