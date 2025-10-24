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

    }

    public List<Vehicle> getVehicleByMakeModel(String makeModel) {
    }

    public List<Vehicle> getVehicleByYear(Integer minYear, Integer maxYear) {
    }

    public List<Vehicle> getVehicleByColor(String color) {
    }

    public List<Vehicle> getVehicleByMileage(Integer minMileage, Integer maxMileage) {
    }

    public List<Vehicle> getVehicleByType(String vehicleType) {
    }

    public List<Vehicle> getAllVehicles() {
        return this.inventory;
    }

    public void addVehicle(Vehicle vehicle) {
        this.inventory.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        this.inventory.remove(vehicle);
    }

}
