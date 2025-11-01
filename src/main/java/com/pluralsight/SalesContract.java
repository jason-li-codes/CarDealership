package com.pluralsight;

import java.time.LocalDate;

public class SalesContract extends Contract {

    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;
    private double totalPrice;
    private boolean isFinanced;
    private double monthlyPayment;

    public SalesContract(LocalDate contractDate, String customerName, String customerEmail,
                         Vehicle vehicleSold, boolean isFinanced) {
        super(contractDate, customerName, customerEmail, vehicleSold);
        setSalesTaxAmount();
        setRecordingFee();
        setProcessingFee();
        setTotalPrice();
        setMonthlyPayment();
    }

    public SalesContract(LocalDate contractDate, String customerName,
                         String customerEmail, Vehicle vehicleSold, double salesTaxAmount, double recordingFee,
                         double processingFee, double totalPrice, boolean isFinanced, double monthlyPayment) {
        super(contractDate, customerName, customerEmail, vehicleSold);
        this.salesTaxAmount = salesTaxAmount;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.totalPrice = totalPrice;
        this.isFinanced = isFinanced;
        this.monthlyPayment = monthlyPayment;
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

    public void setRecordingFee() {
        this.recordingFee = 100;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setProcessingFee() {
        this.processingFee = (isPriceHigh) ? 495 : 295;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    @Override
    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public void setTotalPrice() {
        this.totalPrice = vehicleSold.getPrice() + getSalesTaxAmount() + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        return this.monthlyPayment;
    }

    @Override
    public void setMonthlyPayment() {

        if (isFinanced) {
            double principal = getTotalPrice();
            double interestAnnual = (isPriceHigh) ? 4.25 : 5.25;
            double loanLengthYears = (isPriceHigh) ? 4 : 2;
            // changes interest to a decimal and loan length to be in months
            double interest = interestAnnual / 100 / 12;
            double loanLengthMonths = loanLengthYears * 12;
            // calculates monthly payment
            this.monthlyPayment = principal * ((interest * Math.pow((1 + interest), loanLengthMonths)) /
                    (Math.pow((1 + interest), loanLengthMonths) - 1));
        } else {
            this.monthlyPayment = 0;
        }
    }

}
