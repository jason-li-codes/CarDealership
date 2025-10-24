package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Dealership {

    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Vehicle> getVehicleByPrice(Double minPrice, Double maxPrice) {
        return getVehiclesCustomSearch(minPrice, maxPrice, null, null, null, null, null, null, null, null);
    }

    public List<Vehicle> getVehicleByMakeModel(String make, String model) {
        return getVehiclesCustomSearch(null, null, make, model, null, null, null, null, null, null);
    }

    public List<Vehicle> getVehicleByYear(Integer minYear, Integer maxYear) {
        return getVehiclesCustomSearch(null, null, null, null, minYear, maxYear, null, null, null, null);
    }

    public List<Vehicle> getVehicleByColor(String color) {
        return getVehiclesCustomSearch(null, null, null, null, null, null, color, null, null, null);
    }

    public List<Vehicle> getVehicleByMileage(Integer minMileage, Integer maxMileage) {
        return getVehiclesCustomSearch(null, null, null, null, null, null, null, minMileage, maxMileage, null);
    }

    public List<Vehicle> getVehicleByType(String vehicleType) {
        return getVehiclesCustomSearch(null, null, null, null, null, null, null, null, null, vehicleType);
    }

    public List<Vehicle> getAllVehicles() {
        return this.inventory;
    }

    public List<Vehicle> getVehiclesCustomSearch (Double minPrice, Double maxPrice, String make, String model,
                                                 Integer minYear, Integer maxYear, String color, Integer minMileage,
                                                 Integer maxMileage, String vehicleType) {
        System.out.println("Searching....");
        return inventory.stream()
                .filter(v -> (minPrice == null || v.getPrice() >= minPrice))
                .filter(v -> (maxPrice == null || v.getPrice() <= maxPrice))
                .filter(v -> (make == null || make.isEmpty() || v.getMake().toLowerCase().contains(make)))
                .filter(v -> (model == null || model.isEmpty() || v.getModel().toLowerCase().contains(model)))
                .filter(v -> (minYear == null || v.getYear() >= minYear))
                .filter(v -> (maxYear == null || v.getYear() <= maxYear))
                .filter(v -> (color == null || color.isEmpty() || v.getColor().toLowerCase().contains(color)))
                .filter(v -> (minMileage == null || v.getOdometer() >= minMileage))
                .filter(v -> (maxMileage == null || v.getOdometer() <= maxMileage))
                .filter(v -> (vehicleType == null || vehicleType.isEmpty() ||
                        v.getVehicleType().toLowerCase().contains(vehicleType)))
                .toList();
    }

    public void addVehicle(Vehicle vehicle) {
        this.inventory.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        this.inventory.remove(vehicle);
    }

}