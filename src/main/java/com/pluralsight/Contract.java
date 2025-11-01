package com.pluralsight;

import java.time.LocalDate;

public abstract class Contract {

    protected LocalDate contractDate;
    protected String customerName;
    protected String customerEmail;
    protected Vehicle vehicleSold;

    public Contract(LocalDate contractDate, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.contractDate = contractDate;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    abstract double getTotalPrice();

    abstract void setTotalPrice();

    abstract double getMonthlyPayment();

    abstract void setMonthlyPayment();

}
