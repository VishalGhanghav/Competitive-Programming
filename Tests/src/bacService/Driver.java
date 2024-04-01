package bacService;

import java.util.ArrayList;
import java.util.List;

class Driver {
    String name;
    Location location;
    double ratingSum;
    int totalTrips;
    List<Passenger> passengers;

    public Driver(String name, Location location) {
        this.name = name;
        this.location = location;
        this.ratingSum = 0;
        this.totalTrips = 0;
        this.passengers = new ArrayList<>();
    }

    public double getAverageRating() {
        return totalTrips > 0 ? ratingSum / totalTrips : 0;
    }
}
