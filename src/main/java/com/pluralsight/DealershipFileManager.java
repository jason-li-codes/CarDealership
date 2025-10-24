package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DealershipFileManager {

    public Dealership getDealership() {

        try (BufferedReader bufReader = new BufferedReader(new FileReader("inventory.csv"))) {
            // Reads the first line containing Dealership details
            String dealershipInfo = bufReader.readLine();
            String[] dealershipInfoParsed = dealershipInfo.split("\\|");
            String dealershipName = dealershipInfoParsed[0];
            String dealershipAddress = dealershipInfoParsed[1];
            String dealershipPhone = dealershipInfoParsed[2];
            Dealership dealership = new Dealership(dealershipName, dealershipAddress, dealershipPhone);
            // Loops through the remaining lines in file containing Vehicle details
            String input;
            while ((input = bufReader.readLine()) != null) {
                // Splits the input line by pipe '|' to extract details
                String[] info = input.split("\\|");
                // Parses the Vehicle info
                int vin = Integer.parseInt(info[0]);
                int year = Integer.parseInt(info[1]);
                String make = info[2];
                String model = info[3];
                String vehicleType = info[4];
                String color = info[5];
                int odometer = Integer.parseInt(info[6]);
                double price = Double.parseDouble(info[7]);
                // Creates a new Vehicle object with the extracted information and adds it to ArrayList
                dealership.addVehicle(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));
            }
            return dealership;
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

    public void saveDealership(Dealership dealership) {

    }


}
