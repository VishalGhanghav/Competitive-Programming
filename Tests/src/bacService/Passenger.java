package bacService;

class Passenger {
    String name;
    Location location;
    double ratingSum;
    int totalTrips;

    public Passenger(String name, Location location) {
        this.name = name;
        this.location = location;
        this.ratingSum = 0;
        this.totalTrips = 0;
    }

    public double getAverageRating() {
        return totalTrips > 0 ? ratingSum / totalTrips : 0;
    }
}
