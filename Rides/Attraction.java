package Rides;
public abstract class Attraction {
    static boolean OPEN = true;
    static int NUM_OF_ATTRACTIONS = 0;
    private double price;
    private double utilityCost;
    private int maxRiders;

    private int ticketsAvailible;
    protected volatile boolean isRunning;

    public Attraction(double price, double utilityCost, int maxRiders, 
                      int ticketsAvailible) {
        this.price = price;
        this.utilityCost = utilityCost;
        this.maxRiders = maxRiders;
        this.ticketsAvailible = ticketsAvailible;
        NUM_OF_ATTRACTIONS++;
    }
    public Attraction() {
        this.price = 0;
        this.utilityCost = 0;
        this.maxRiders = 0;
        this.ticketsAvailible = 0;
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
    public boolean reduceTicketsAvailible() {
        if (this.ticketsAvailible > 0 && this.ticketsAvailible >= this.maxRiders) {
            this.ticketsAvailible -= this.maxRiders;
            return true;
        }
        if (this.ticketsAvailible > 0 && this.ticketsAvailible < this.maxRiders) {
            System.out.println("Not enough tickets available to reduce by max riders. Reducing by available tickets.");
            this.ticketsAvailible = 0; // All tickets are used up
            return true;
        }
        System.out.println("No tickets available to reduce.");
        return false;
    }
    public double calculateRevanue() {
        return (double)(this.price * this.maxRiders - this.utilityCost);
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
        if (!isOpen()) {
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