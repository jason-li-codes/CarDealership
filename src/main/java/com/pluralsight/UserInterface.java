package com.pluralsight;

import java.time.LocalDate;
import java.util.*;

public class UserInterface {

    private Dealership dealership;
    private List<Contract> contractList;
    private Scanner input;

    private void init() {
        this.dealership = DealershipFileManager.getDealership();
        this.contractList = ContractFileManager.getContracts();
        input = new Scanner(System.in);
    }

    public void display() {

        init();

        while (true) {
            System.out.printf("""
                    Hello, welcome to %s!
                    ======================MAIN MENU======================
                    What would you like to do today?
                    (1) See all vehicles
                    (2) Search vehicles by specification
                    (3) Add a vehicle to the dealership
                    (4) Remove a vehicle from the dealership
                    (5) See all contracts
                    (6) Sign new contract
                    (0) Exit dealership program
                    """, dealership.getName());

            char menuOption = getValidString().charAt(0);
            switch (menuOption) {
                case '1' -> processGetAllVehiclesRequest();
                case '2' -> searchSubMenu();
                case '3' -> processAddVehicleRequest();
                case '4' -> processRemoveVehicleRequest();
                case '5' -> processGetAllContractsRequest();
                case '6' -> processSignNewContractRequest();
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

    private void searchSubMenu() {
        while (true) {
            System.out.println("""
                    ======================SEARCH MENU======================
                    What specification would you like to use to search?
                    (1) See vehicles by price
                    (2) See vehicles by make/model
                    (3) See vehicles by year
                    (4) See vehicles by color
                    (5) See vehicles by mileage
                    (6) See vehicles by vehicle type
                    (0) Return to main menu
                    """);

            char menuOption = getValidString().charAt(0);
            switch (menuOption) {
                case '1' -> processGetByPriceRequest();
                case '2' -> processGetByMakeModelRequest();
                case '3' -> processGetByYearRequest();
                case '4' -> processGetByColorRequest();
                case '5' -> processGetByMileageRequest();
                case '6' -> processGetByVehicleTypeRequest();
                case '0' -> {
                    System.out.println("Returning to main menu....");
                    return;
                }
                default -> System.out.println("Invalid choice. Please select a valid option.");
            }
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
        System.out.println("Enter make:");
        String makeInput = input.nextLine().trim().toLowerCase();
        System.out.println("Enter model:");
        String modelInput = input.nextLine().trim().toLowerCase();

        displayList(dealership.getVehicleByMakeModel(makeInput, modelInput));
    }

    private void processGetByYearRequest() {

        // Gets the minimum and maximum year from the user
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

        // Gets the color input from the user, converting it to lowercase for case-insensitive comparison
        System.out.println("Enter color:");
        String colorInput = input.nextLine().trim().toLowerCase();

        displayList(dealership.getVehicleByColor(colorInput));
    }

    private void processGetByMileageRequest() {

        // Gets the minimum and maximum mileage from the user
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

        // Gets the vehicle type input from the user, converting it to lowercase for case-insensitive comparison
        System.out.println("Enter vehicle type:");
        String typeInput = input.nextLine().trim().toLowerCase();

        displayList(dealership.getVehicleByType(typeInput));
    }

    private void processGetAllVehiclesRequest() {
        displayList(dealership.getAllVehicles());
    }

    private void processAddVehicleRequest() {

        while (true) {
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

            Vehicle addedVehicle = new Vehicle(newVin, newYear, newMake,
                    newModel, newType, newColor, newOdometer, newPrice);
            List<Vehicle> addedVehicleList = Collections.singletonList(addedVehicle);
            displayList(addedVehicleList);

            System.out.println("""
                    Is this the vehicle you would like to add?
                    (Y) Yes
                    (N) No, I need to enter information again
                    (X) Exit to main menu
                    """);

            char removeOption = getValidString().charAt(0);
            switch (removeOption) {
                case 'Y':
                    dealership.addVehicle(addedVehicle);
                    DealershipFileManager.saveDealership(dealership);
                    System.out.println("Vehicle successfully added.");
                    return;
                case 'N':
                    continue;
                case 'X':
                    System.out.println("Returning to main menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private void displayList(List<Vehicle> vehicleList) {

        if (vehicleList.isEmpty()) {
            System.out.println("There are no vehicles to display.");
        } else {
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
    }

    private void processRemoveVehicleRequest() {

        while (true) {
            List<Vehicle> allVehicles = dealership.getAllVehicles();
            displayList(allVehicles);
            System.out.println("Which vehicle would you like to remove?");
            int removeIndex = getValidInt();

            List<Vehicle> chosenVehicle = Collections.singletonList(allVehicles.get(removeIndex - 1));
            displayList(chosenVehicle);
            System.out.println("""
                    Is this the vehicle you would like to remove?
                    (Y) Yes
                    (N) No, I need to select a different vehicle
                    (X) Exit to main menu
                    """);

            char removeOption = getValidString().charAt(0);
            switch (removeOption) {
                case 'Y':
                    dealership.removeVehicle(chosenVehicle.get(0));
                    DealershipFileManager.saveDealership(dealership);
                    System.out.println("Vehicle successfully removed.");
                    return;
                case 'N':
                    continue;
                case 'X':
                    System.out.println("Returning to main menu...");
                    return;
            }
        }
    }

    private void processGetAllContractsRequest() {
        displayListContract(contractList);
    }

    private void displayListContract(List<Contract> contracts) {

        if (contracts.isEmpty()) {
            System.out.println("There are no contracts to display.");
        } else {
            System.out.printf("%-8s %-12s %-20s %-25s %-10s %-8s %-10s %-10s %-10s %-10s %-12s\n",
                    "Type", "Date", "Customer Name", "Email", "VIN", "Year", "Make", "Model", "Price", "Total", "Monthly");
            System.out.println("+--------+------------+--------------------+-------------------------+----------+--------+----------+----------+----------+----------+------------+");
            for (Contract c : contracts) {
                System.out.printf("%-8s %-12s %-20s %-25s %-10d %-8d %-10s %-10s %10.2f %10.2f %10.2f\n",
                        (c instanceof SalesContract ? "SALE" : "LEASE"), c.getContractDate(), c.getCustomerName(),
                        c.getCustomerEmail(), c.getVehicleSold().getVin(), c.getVehicleSold().getYear(),
                        c.getVehicleSold().getMake(), c.getVehicleSold().getModel(), c.getVehicleSold().getPrice(),
                        c.getTotalPrice(), c.getMonthlyPayment()
                );
            }
            System.out.println("+--------+------------+--------------------+-------------------------+----------+--------+----------+----------+----------+----------+------------+");
        }
    }

    private void processSignNewContractRequest() {

        System.out.println("""
                ======================CONTRACT MENU======================
                What kind of contract are you hoping to sign?
                (S) Sales contract
                (L) Lease contract
                """);
        char contractMenuOption = getValidString().charAt(0);

        LocalDate contractDate = LocalDate.now();
        System.out.println("What is your name?");
        String customerName = getValidString();
        System.out.println("What is your email?");
        String customerEmail = getValidString();

        Vehicle chosenVehicle = null;
        while (true) {
            System.out.println("What is the VIN of the vehicle you would like to sign for?");
            int vin = getValidInt();
            for (Vehicle v : dealership.getAllVehicles()) {
                if (v.getVin() == vin) {
                    chosenVehicle = v;
                }
            }
            if (chosenVehicle == null) {
                System.out.println("""
                        That vehicle does not exist in our system.
                        (T) Try another VIN
                        (X) Exit to main menu
                        """);
            } else {
                List<Vehicle> chosenVehicleList = Collections.singletonList(chosenVehicle);
                displayList(chosenVehicleList);
                System.out.println("""
                        Is this the vehicle you would like to sign for?
                        (Y) Yes
                        (N) No, I need to select a different vehicle
                        (X) Exit to main menu
                        """);
            }
            char contractSignOption = getValidString().charAt(0);
            switch (contractSignOption) {
                case 'Y':
                    break;
                case 'T':
                case 'N':
                    continue;
                case 'X':
                    System.out.println("Returning to main menu...");
                    return;
            }
        }

        switch (contractMenuOption) {
            case 'S':
                processSignNewSalesContract(contractDate, customerName, customerEmail, chosenVehicle);
                break;
            case 'L':
                processSignNewLeaseContract(contractDate, customerName, customerEmail, chosenVehicle);
                break;
            default:
                System.out.println("Invalid choice. Please select a valid option.");

        }
    }

    private void processSignNewSalesContract(LocalDate contractDate, String customerName,
                                             String customerEmail, Vehicle vehicle) {

        System.out.println("""
                Are you open to financing your new vehicle?
                (Y) Yes
                (N) No
                """);
        char SalesContractSignOption = getValidString().charAt(0);
        boolean isFinanced = false;
        switch (SalesContractSignOption) {
            case 'Y':
                isFinanced = true;
            case 'N':
                break;
            default:
                System.out.println("Invalid choice. Please select a valid option.");
        }

        SalesContract newContract = new SalesContract(contractDate, customerName, customerEmail, vehicle, isFinanced);
        List<Contract> newContractList = Collections.singletonList(newContract);
        displayListContract(newContractList);
        System.out.println("""
                Is all this information correct?
                (Y) Yes
                (N) No, I need to re-enter information
                """);
        char SalesContractConfirmOption = getValidString().charAt(0);
        switch (SalesContractConfirmOption) {
            case 'Y':
                contractList.add(newContract);
                ContractFileManager.saveContracts((ArrayList<Contract>) contractList);
                System.out.println("Contract saved successfully. Returning to main menu....");
            case 'N':
                return;
            default:
                System.out.println("Invalid choice. Please select a valid option.");
        }
    }

    private static void processSignNewLeaseContract(LocalDate contractDate, String customerName,
                                                    String customerEmail, Vehicle vehicle) {

    }

}
