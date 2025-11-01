package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class ContractFileManager {

    public static ArrayList<Contract> getContracts() {

        try (BufferedReader bufReader = new BufferedReader(new FileReader("contracts.csv"))) {

            ArrayList<Contract> contracts = new ArrayList<>();
            // Loops through lines in file containing Contract details
            String input;
            while ((input = bufReader.readLine()) != null) {
                // Splits the input line by pipe '|' to extract details
                String[] info = input.split("\\|");
                // Parses the Contract info
                String contractType = info[0];
                LocalDate contractDate = LocalDate.parse(info[1]);
                String customerName = info[2];
                String customerEmail = info[2];
                Vehicle vehicleSold = getVehicleSold(info);

                if (Objects.equals(contractType, "SALE")) {
                    double salesTaxAmount = Double.parseDouble(info[11]);
                    double recordingFee = Double.parseDouble(info[12]);
                    double processingFee = Double.parseDouble(info[13]);
                    double totalPrice = Double.parseDouble(info[14]);
                    boolean isFinanced = Objects.equals(info[15], "YES");
                    double monthlyPayment = Double.parseDouble(info[16]);

                    contracts.add(new SalesContract(contractDate, customerName, customerEmail, vehicleSold,
                            salesTaxAmount, recordingFee, processingFee, totalPrice, isFinanced, monthlyPayment));
                } else {
                    double expectedEndingValue = Double.parseDouble(info[11]);
                    double leaseFee = Double.parseDouble(info[12]);
                    double totalPrice = Double.parseDouble(info[13]);
                    double monthlyPayment = Double.parseDouble(info[14]);
                    contracts.add(new LeaseContract(contractDate, customerName, customerEmail, vehicleSold,
                            expectedEndingValue, leaseFee, totalPrice, monthlyPayment));
                }
            }
            return contracts;
        } catch (FileNotFoundException e) { // Exits application if FileNotFoundException is reached
            System.out.println("Sorry, there's a problem finding your file, please try again later.");
            e.getStackTrace();
        } catch (IOException e) {
            System.out.println("Sorry, there was a problem reading your file, please try again later.");
            e.getStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Sorry, there was a problem getting numerical info from file, please try again later.");
            e.getStackTrace();
        }
        return null;
    }

    private static Vehicle getVehicleSold(String[] info) {
        int vin = Integer.parseInt(info[3]);
        int year = Integer.parseInt(info[4]);
        String make = info[5];
        String model = info[6];
        String vehicleType = info[7];
        String color = info[8];
        int odometer = Integer.parseInt(info[9]);
        double vehiclePrice = Double.parseDouble(info[10]);
        return new Vehicle(vin, year, make, model, vehicleType, color, odometer, vehiclePrice);
    }



}
