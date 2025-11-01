package com.pluralsight;

import java.time.LocalDate;

public class LeaseContract extends Contract {

    private double expectedEndingValue;
    private double leaseFee;
    private double totalPrice;
    private double monthlyPayment;

    public LeaseContract(LocalDate contractDate, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(contractDate, customerName, customerEmail, vehicleSold);
        setExpectedEndingValue();
        setLeaseFee();
        setTotalPrice();
        setMonthlyPayment();
    }

    public LeaseContract(LocalDate contractDate, String customerName, String customerEmail, Vehicle vehicleSold,
                         double expectedEndingValue, double leaseFee, double totalPrice, double monthlyPayment) {
        super(contractDate, customerName, customerEmail, vehicleSold);
        this.expectedEndingValue = expectedEndingValue;
        this.leaseFee = leaseFee;
        this.totalPrice = totalPrice;
        this.monthlyPayment = monthlyPayment;
    }

    public void setExpectedEndingValue() {
        double endingValuePercentage = 50;
        this.expectedEndingValue = vehicleSold.getPrice() * (endingValuePercentage / 100);
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setLeaseFee() {
        double leaseFeePercentage = 7;
        this.leaseFee = vehicleSold.getPrice() * (leaseFeePercentage / 100);
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    @Override
    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public void setTotalPrice() {
        totalPrice = vehicleSold.getPrice() - getExpectedEndingValue() + getLeaseFee();
    }

    @Override
    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    @Override
    public void setMonthlyPayment() {
        double interestAnnual = 4;
        double loanLengthYears = 3;
        double interest = interestAnnual / 100 / 12;
        double loanLengthMonths = loanLengthYears * 12;
        this.monthlyPayment = (vehicleSold.getPrice() - getExpectedEndingValue()) *
                ((interest * Math.pow((1 + interest), loanLengthMonths)) /
                (Math.pow((1 + interest), loanLengthMonths) - 1));
    }

    @Override
    public String toCsvFormat() {
        return String.join("|",
                "LEASE",
                contractDate.toString(),
                customerName,
                customerEmail,
                Integer.toString(vehicleSold.getVin()),
                Integer.toString(vehicleSold.getYear()),
                vehicleSold.getMake(),
                vehicleSold.getModel(),
                vehicleSold.getVehicleType(),
                vehicleSold.getColor(),
                Integer.toString(vehicleSold.getOdometer()),
                String.format("%.2f", vehicleSold.getPrice()),
                String.format("%.2f", expectedEndingValue),
                String.format("%.2f", leaseFee),
                String.format("%.2f", totalPrice),
                String.format("%.2f", monthlyPayment)
        );
    }

}
