package com.pluralsight;

import java.io.*;
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
                String customerEmail = info[3];
                Vehicle vehicleSold = getVehicleSold(info);

                if (Objects.equals(contractType, "SALE")) {
                    double salesTaxAmount = Double.parseDouble(info[12]);
                    double recordingFee = Double.parseDouble(info[13]);
                    double processingFee = Double.parseDouble(info[14]);
                    double totalPrice = Double.parseDouble(info[15]);
                    boolean isFinanced = Objects.equals(info[16], "YES");
                    double monthlyPayment = Double.parseDouble(info[17]);

                    contracts.add(new SalesContract(contractDate, customerName, customerEmail, vehicleSold,
                            salesTaxAmount, recordingFee, processingFee, totalPrice, isFinanced, monthlyPayment));
                } else {
                    double expectedEndingValue = Double.parseDouble(info[12]);
                    double leaseFee = Double.parseDouble(info[13]);
                    double totalPrice = Double.parseDouble(info[14]);
                    double monthlyPayment = Double.parseDouble(info[15]);
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
        int vin = Integer.parseInt(info[4]);
        int year = Integer.parseInt(info[5]);
        String make = info[6];
        String model = info[7];
        String vehicleType = info[8];
        String color = info[9];
        int odometer = Integer.parseInt(info[10]);
        double vehiclePrice = Double.parseDouble(info[11]);
        return new Vehicle(vin, year, make, model, vehicleType, color, odometer, vehiclePrice);
    }

    public static void saveContracts(ArrayList<Contract> contracts) {

        try (BufferedWriter bufWriter = new BufferedWriter(new FileWriter("contracts.csv"))) {
            for (Contract contract : contracts) { // Writes each Contract to file
                bufWriter.write(contract.toCsvFormat() + "\n");
            }
            System.out.println("Contracts file updated successfully.");
        } catch (FileNotFoundException e) { // Handles FileNotFoundException
            System.out.println("Sorry, there's a problem finding the contracts file, please try again later.");
        } catch (IOException e) { // Handles other IOExceptions
            System.out.println("Sorry, there's a problem updating the contracts file, please try again later.");
        }
    }

}
