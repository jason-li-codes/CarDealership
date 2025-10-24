package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private Dealership dealership;
    private Scanner input;

    private void init() {
        this.dealership = DealershipFileManager.getDealership();
        input = new Scanner(System.in);
    }

    public void display() {

        init();

        while (true) {
            System.out.printf("""
                    Hello, welcome to %s!
                    What would you like to do today?
                    (1) See vehicles by price
                    (2) See vehicles by make/model
                    (3) See vehicles by year
                    (4) See vehicles by color
                    (5) See vehicles by mileage
                    (6) See vehicles by vehicle type
                    (7) See all vehicles
                    (8) Sell a vehicle to the dealership
                    (9) Buy a vehicle from the dealership
                    (0) Exit dealership program
                    """, dealership.getName());

            char menuOption = getValidString().charAt(0);
            switch (menuOption) {
                case '1' -> processGetByPriceRequest();
                case '2' -> processGetByMakeModelRequest();
                case '3' -> processGetByYearRequest();
                case '4' -> processGetByColorRequest();
                case '5' -> processGetByMileageRequest();
                case '6' -> processGetByVehicleTypeRequest();
                case '7' -> processGetAllVehiclesRequest();
                case '8' -> processAddVehicleRequest();
                case '9' -> processRemoveVehicleRequest();
                case '0' -> {
                    System.out.println("EXITING PROGRAM....");
                    input.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private String getValidString() {
        String string;
        do {
            string = input.nextLine().trim();
            if (string.isEmpty()) {
                System.out.println("You have not entered anything, please try again.");
            }
        } while (string.isEmpty());
        return string;
    }

    private double getValidDouble() {

        String userInputDouble = null;
        Double inputDouble = null;

        while (true) {
            userInputDouble = input.nextLine(); // Accepts next user input as a String
            inputDouble = attemptParseDouble(userInputDouble); // calls attemptParseDouble to check for valid double
            if (inputDouble != null) {
                inputDouble = Math.round(inputDouble * 100) / 100.0;  // Rounds it to 2 decimal points if parse succeeds
                return inputDouble;
            } else {
                System.out.println("Sorry I don't know what you mean, please try again.");
            }
        }
    }

    private Double attemptParseDouble(String num) {

        try {
            return Double.parseDouble(num); // Tries to parse input String as a double number
        } catch (Exception e) {
            return null; // Returns null if parse attempt fails
        }
    }

    private int getValidInt() {

        String userInputInt = null;
        Integer inputInt = null;

        while (true) {
            userInputInt = input.nextLine(); // Accepts next user input as a String
            inputInt = attemptParseInt(userInputInt); // calls attemptParseInt to check for valid int
            if (inputInt != null) {
                return inputInt; // Returns inputInt if parse succeeds
            } else {
                System.out.println("Sorry I don't know what you mean, please try again.");
            }
        }
    }

    private Integer attemptParseInt(String num) {

        try {
            return Integer.parseInt(num); // Tries to parse input String as an int number
        } catch (Exception e) {
            return null; // Returns null if parse attempt fails
        }
    }

    private void processGetByPriceRequest() {

        // Gets the minimum and maximum price from the user
        Double minPriceInput = null;
        System.out.println("Enter minimum price:");
        String amountMinInputStr = input.nextLine().trim();
        if (!amountMinInputStr.isEmpty()) {
            minPriceInput = attemptParseDouble(amountMinInputStr);
        }
        Double maxPriceInput = null;
        System.out.println("Enter maximum price:");
        String amountMaxInputStr = input.nextLine().trim();
        if (!amountMaxInputStr.isEmpty()) {
            maxPriceInput = attemptParseDouble(amountMaxInputStr);
        }
        // Swaps minimum and maximum if necessary
        if (minPriceInput != null && maxPriceInput != null && minPriceInput > maxPriceInput) {
            Double temp = minPriceInput;
            minPriceInput = maxPriceInput;
            maxPriceInput = temp;
        }

        displayList(dealership.getVehicleByPrice(minPriceInput, maxPriceInput));
    }

    private void processGetByMakeModelRequest() {

        // Gets the make/model input from the user, converting it to lowercase for case-insensitive comparison
        System.out.println("Enter make/model:");
        String makeModelInput = input.nextLine().trim().toLowerCase();

        displayList(dealership.getVehicleByMakeModel(makeModelInput));
    }

    private void processGetByYearRequest() {

        // Gets the minimum and maximum price from the user
        Integer minYearInput = null;
        System.out.println("Enter minimum year:");
        String minYearInputStr = input.nextLine().trim();
        if (!minYearInputStr.isEmpty()) {
            minYearInput = attemptParseInt(minYearInputStr);
        }
        Integer maxYearInput = null;
        System.out.println("Enter maximum price:");
        String maxYearInputStr = input.nextLine().trim();
        if (!maxYearInputStr.isEmpty()) {
            maxYearInput = attemptParseInt(maxYearInputStr);
        }
        // Swaps minimum and maximum if necessary
        if (minYearInput != null && maxYearInput != null && minYearInput > maxYearInput) {
            Integer temp = minYearInput;
            minYearInput = maxYearInput;
            maxYearInput = temp;
        }

        displayList(dealership.getVehicleByYear(minYearInput, maxYearInput));
    }

    private void processGetByColorRequest() {

        // Gets the make/model input from the user, converting it to lowercase for case-insensitive comparison
        System.out.println("Enter color:");
        String colorInput = input.nextLine().trim().toLowerCase();

        displayList(dealership.getVehicleByColor(colorInput));
    }

    private void processGetByMileageRequest() {

        // Gets the minimum and maximum price from the user
        Integer minMileageInput = null;
        System.out.println("Enter minimum year:");
        String minMileageInputStr = input.nextLine().trim();
        if (!minMileageInputStr.isEmpty()) {
            minMileageInput = attemptParseInt(minMileageInputStr);
        }
        Integer maxMileageInput = null;
        System.out.println("Enter maximum price:");
        String maxMileageInputStr = input.nextLine().trim();
        if (!maxMileageInputStr.isEmpty()) {
            maxMileageInput = attemptParseInt(maxMileageInputStr);
        }
        // Swaps minimum and maximum if necessary
        if (minMileageInput != null && maxMileageInput != null && minMileageInput > maxMileageInput) {
            Integer temp = minMileageInput;
            minMileageInput = maxMileageInput;
            maxMileageInput = temp;
        }

        displayList(dealership.getVehicleByMileage(minMileageInput, maxMileageInput));
    }

    private void processGetByVehicleTypeRequest() {

        // Gets the make/model input from the user, converting it to lowercase for case-insensitive comparison
        System.out.println("Enter vehicle type:");
        String typeInput = input.nextLine().trim().toLowerCase();

        displayList(dealership.getVehicleByType(typeInput));
    }

    private void processGetAllVehiclesRequest() {
        displayList(dealership.getAllVehicles());
    }

    private void processAddVehicleRequest() {

        System.out.println("Enter vehicle VIN: ");
        int newVin = getValidInt();
        System.out.println("Enter vehicle year:");
        int newYear = getValidInt();
        System.out.println("Enter vehicle make:");
        String newMake = getValidString();
        System.out.println("Enter vehicle model:");
        String newModel = getValidString();
        System.out.println("Enter vehicle type (ie. truck, sedan, SUV, etc.):");
        String newType = getValidString();
        System.out.println("Enter vehicle color:");
        String newColor = getValidString();
        System.out.println("Enter vehicle odometer reading:");
        int newOdometer = getValidInt();
        System.out.println("Enter vehicle price:");
        double newPrice = getValidDouble();

        dealership.addVehicle(new Vehicle(newVin, newYear, newMake,
                newModel, newType, newColor, newOdometer, newPrice));
    }

    private void displayList(List<Vehicle> vehicleList) {

        System.out.printf("%-5s %-10s %-6s %-12s %-15s %-15s %-12s %-10s %-10s%n",
                "No.", "VIN", "Year", "Make", "Model", "Type", "Color", "Odometer", "Price");
        System.out.println("+-----+----------+------+------------+---------------+---------------+------------+----------+----------+");
        for (int i = 0; i < vehicleList.size(); i++) {
            Vehicle v = vehicleList.get(i);
            System.out.printf("|%-5d|%-10d|%-6d|%-12s|%-15s|%-15s|%-12s|%-10d|%10.2f|\n",
                    i + 1, v.getVin(), v.getYear(), v.getMake(), v.getModel(),
                    v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice());
        }
        System.out.println("+-----+----------+------+------------+---------------+---------------+------------+----------+----------+");
    }

    private void processRemoveVehicleRequest() {

        List<Vehicle> allVehicles = dealership.getAllVehicles();
        displayList(allVehicles);
        System.out.println("Which vehicle would you like to purchase?");
        int purchaseIndex = getValidInt();

        dealership.removeVehicle(allVehicles.get(purchaseIndex - 1));
    }


}
