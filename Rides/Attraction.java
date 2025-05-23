package Rides;
public abstract class Attraction {
    static boolean OPEN = true;
    static int NUM_OF_ATTRACTIONS = 0;
    private double price;
    private double utilityCost;
    private int maxRiders;

    private int ticketsAvailible;
    private int numberOfAttendants;

    private int hoursPerDay;
    protected boolean isRunning;

    public Attraction(double price, double utilityCost, int maxRiders, 
                      int ticketsAvailible, int numberOfAttendants, 
                      int hoursPerDay) {
        this.price = price;
        this.utilityCost = utilityCost;
        this.maxRiders = maxRiders;
        this.ticketsAvailible = ticketsAvailible;
        this.numberOfAttendants = numberOfAttendants;
        this.hoursPerDay = hoursPerDay;
        NUM_OF_ATTRACTIONS++;
    }
     public Attraction(double price, double utilityCost, int maxRiders, int numberOfAttendants, 
                      int hoursPerDay) {
        this.price = price;
        this.utilityCost = utilityCost;
        this.maxRiders = maxRiders;
        this.ticketsAvailible = maxRiders;
        this.numberOfAttendants = numberOfAttendants;
        this.hoursPerDay = hoursPerDay;
        NUM_OF_ATTRACTIONS++;
    }
    public Attraction() {
        this.price = 0;
        this.utilityCost = 0;
        this.maxRiders = 0;
        this.ticketsAvailible = 0;
        this.numberOfAttendants = 0;
        this.hoursPerDay = 0;
        NUM_OF_ATTRACTIONS++;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getUtilityCost() {
        return utilityCost;
    }
    public void setUtilityCost(double utilityCost) {
        this.utilityCost = utilityCost;
    }
    public int getMaxRiders() {
        return maxRiders;
    }
    public void setMaxRiders(int maxRiders) {
        this.maxRiders = maxRiders;
    }
    public int getTicketsAvailible() {
        return ticketsAvailible;
    }
    public void setTicketsAvailible(int ticketsAvailible) {
        this.ticketsAvailible = ticketsAvailible;
    }
    public int getNumberOfAttendants() {
        return numberOfAttendants;
    }
    public void setNumberOfAttendants(int numberOfAttendants) {
        this.numberOfAttendants = numberOfAttendants;
    }
    public int getHoursPerDay() {
        return hoursPerDay;
    }
    public void setHoursPerDay(int hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }
    
    public static boolean isOpen() {
        return OPEN;
    }
    public static void closeRides() {
        OPEN = false;
    }
    protected final Runnable checkIfRunning = () -> {
        if (isRunning) {
            System.out.println("The ride is already running!");
            throw new IllegalStateException("The ride is already running!");
        }
    };
    protected final Runnable checkIfOpen = () -> {
        if (!OPEN) {
            System.out.println("The ride is closed!");
            throw new IllegalStateException("The ride is closed!");
        }
    };
    protected final Runnable checkIfMaxRidersExceeded = () -> {
        if (ticketsAvailible <= 0) {
            System.out.println("The ride is full!");
            throw new IllegalStateException("The ride is full!");
        }
    };
}