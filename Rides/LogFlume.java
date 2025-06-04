package Rides;

public class LogFlume extends RollerCoaster {
    private double waterLevel; // in meters
    private boolean isWet; // true if the ride gets the riders wet
    private final double waterLevelThreshold = 0.5; // threshold for water level to be considered wet
    private final boolean barfBagNeeded = false; // Log Flume does not require a barf bag, just puke in the water

    public LogFlume(double price, double utilityCost, int maxRiders, 
                    int ticketsAvailable, int minRiders, double speed, 
                    double height, double length,
                    int numOfCars, int seatsPerCar, double waterLevel) {
        super(price, utilityCost, maxRiders, ticketsAvailable, speed, height, length, numOfCars, seatsPerCar);
        super.setBarfBagNeeded(this.barfBagNeeded);
        this.waterLevel = waterLevel;
        this.isWet = waterLevel > waterLevelThreshold;
    }

    public LogFlume() {
        super();
        this.waterLevel = 0;
        this.isWet = false;
    }

    public double getWaterLevel() {
        return this.waterLevel;
    }

    public void setWaterLevel(double waterLevel) {
        this.waterLevel = waterLevel;
    }

    public boolean isWet() {
        return this.isWet;
    }

    public void setWet(boolean isWet) {
        this.isWet = isWet;
    }
    public double getWaterLevelThreshold() {
        return waterLevelThreshold;
    }
    @Override 
    public String toString() {
        return "LogFlume: \n" +
               "Water Level: " + waterLevel + " meters\n" +
               "Is Wet: " + isWet + "\n" +
               super.toString();
    }


}