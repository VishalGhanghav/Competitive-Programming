package bacService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CabService {
    private Map<String, Driver> drivers = new HashMap<>();
    private Map<String, Passenger> passengers = new HashMap<>();

    public void addDriver(String name, double x, double y) {
        drivers.put(name, new Driver(name, new Location(x, y)));
    }

    public void addPassenger(String name, double x, double y) {
        passengers.put(name, new Passenger(name, new Location(x, y)));
    }

    public void addTrip(String driverName, int driverRating, String passengerName, int passengerRating) {
        Driver driver = drivers.get(driverName);
        Passenger passenger = passengers.get(passengerName);

        if (driver != null && passenger != null) {
            driver.ratingSum += driverRating;
            driver.totalTrips++;
            passenger.ratingSum += passengerRating;
            passenger.totalTrips++;
            driver.passengers.add(passenger);
        }
    }

    public void printPassengerInfo(String passengerName) {
        Passenger passenger = passengers.get(passengerName);

        if (passenger != null) {
            System.out.println("Average Rating of Passenger " + passengerName + ": " + passenger.getAverageRating());
            System.out.println("Eligible Drivers (Sorted by Rating):");
            printEligibleDriversSortedByRating(passenger);
            System.out.println("Eligible Drivers (Sorted by Distance):");
            printEligibleDriversSortedByDistance(passenger);
        } else {
            System.out.println("Passenger not found.");
        }
    }

    private void printEligibleDriversSortedByRating(Passenger passenger) {
        List<Driver> eligibleDrivers = new ArrayList<>();
        for (Driver driver : drivers.values()) {
            if (driver.getAverageRating() >= passenger.getAverageRating()) {
                eligibleDrivers.add(driver);
            }
        }

        eligibleDrivers.sort(Comparator.comparing(Driver::getAverageRating).reversed());
        printDriverList(eligibleDrivers);
    }

    private void printEligibleDriversSortedByDistance(Passenger passenger) {
        List<Driver> eligibleDrivers = new ArrayList<>();
        for (Driver driver : drivers.values()) {
            if (driver.getAverageRating() >= passenger.getAverageRating()) {
                eligibleDrivers.add(driver);
            }
        }

        eligibleDrivers.sort(Comparator.comparingDouble(driver -> calculateDistance(driver.location, passenger.location)));
        printDriverList(eligibleDrivers);
    }

    private void printDriverList(List<Driver> drivers) {
        for (Driver driver : drivers) {
            System.out.println(driver.name + ", " + driver.getAverageRating());
        }
    }

    private double calculateDistance(Location location1, Location location2) {
        return Math.sqrt(Math.pow(location2.x - location1.x, 2) + Math.pow(location2.y - location1.y, 2));
    }

    public static void main(String[] args) {
        CabService cabService = new CabService();

        // Add drivers
        cabService.addDriver("D1", 1, 0);
        cabService.addDriver("D2", 2, 0);
        cabService.addDriver("D3", 3, 0);
        cabService.addDriver("D4", 4, 0);

        // Add passengers
        cabService.addPassenger("C1", 1, 0);

        // Add trip data
        cabService.addTrip("D1", 4, "C1", 5);
        cabService.addTrip("D1", 5, "C2", 4);
        cabService.addTrip("D1", 1, "C3", 2);
        cabService.addTrip("D2", 5, "C1", 1);
        cabService.addTrip("D2", 5, "C2", 5);
        cabService.addTrip("D2", 4, "C3", 5);
        cabService.addTrip("D3", 3, "C1", 2);
        cabService.addTrip("D3", 4, "C2", 5);
        cabService.addTrip("D3", 3, "C3", 3);

        // Print passenger info
        cabService.printPassengerInfo("C1");
    }
}
