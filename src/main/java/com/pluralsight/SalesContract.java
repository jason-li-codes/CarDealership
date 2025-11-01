package com.pluralsight;

import java.time.LocalDate;

public class SalesContract extends Contract {

    private boolean isFinanced;
    private boolean isPriceHigh = vehicleSold.getPrice() >= 10000;

    public SalesContract(LocalDate contractDate, String customerName,
                         String customerEmail, Vehicle vehicleSold, boolean isFinanced) {
        super(contractDate, customerName, customerEmail, vehicleSold);
        this.isFinanced = isFinanced;
    }

    public double getSalesTax() {
        double salesTax = 0.05;
        return vehicleSold.getPrice() * salesTax;
    }

    @Override
    public double getTotalPrice() {

        double recordingFee = 100;
        double processingFee = (isPriceHigh) ? 495 : 295;

        return vehicleSold.getPrice() + getSalesTax() + recordingFee + processingFee;
        }

    @Override
    public double getMonthlyPayment() {

        if (isFinanced) {
            double principal = vehicleSold.getPrice();
            double interestAnnual = (isPriceHigh) ? 4.25 : 5.25;
            double loanLengthYears = (isPriceHigh) ? 4 : 2;
            // changes interest to a decimal and loan length to be in months
            double interest = interestAnnual / 100 / 12;
            double loanLengthMonths = loanLengthYears * 12;
            // calculates monthly payment
            return principal * ((interest * Math.pow((1 + interest), loanLengthMonths)) /
                    (Math.pow((1 + interest), loanLengthMonths) - 1));
        } else {
            return 0;
        }
    }

}
