package com.pluralsight;

import java.time.LocalDate;

public class SalesContract extends Contract {

    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;
    private boolean isFinanced;


    public SalesContract(LocalDate contractDate, String customerName,
                         String customerEmail, Vehicle vehicleSold, double salesTaxAmount, double recordingFee,
                         double processingFee, boolean isFinanced) {
        super(contractDate, customerName, customerEmail, vehicleSold);
        this.salesTaxAmount = salesTaxAmount;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.isFinanced = isFinanced;
    }

    private final boolean isPriceHigh = vehicleSold.getPrice() >= 10000;

    public boolean isFinanced() {
        return isFinanced;
    }

    public void setFinanced(boolean financed) {
        isFinanced = financed;
    }

    public void setSalesTaxAmount() {
        double salesTaxPercentage = 0.05;
        this.salesTaxAmount = vehicleSold.getPrice() * salesTaxPercentage;
    }

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    @Override
    public double getTotalPrice() {
        return vehicleSold.getPrice() + getSalesTaxAmount() + recordingFee + processingFee;
        }

    @Override
    public double getMonthlyPayment() {

        if (isFinanced) {
            double principal = getTotalPrice();
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
