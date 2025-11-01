package com.pluralsight;

import java.time.LocalDate;

public class SalesContract extends Contract {

    private boolean isFinanced;

    public SalesContract(LocalDate contractDate, String customerName,
                         String customerEmail, Vehicle vehicleSold, boolean isFinanced) {
        super(contractDate, customerName, customerEmail, vehicleSold);
        this.isFinanced = isFinanced;
    }

    @Override
    public double getTotalPrice() {

        return 0;
    }

    @Override
    public double getMonthlyPayment() {
        return 0;
    }

}
