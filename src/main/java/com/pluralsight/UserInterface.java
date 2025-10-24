package com.pluralsight;

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

}
