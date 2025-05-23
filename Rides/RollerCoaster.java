package Rides;
import java.util.Timer;
import java.util.TimerTask;
public class RollerCoaster extends Attraction {
    private int numOfCars;
    private int seatsPerCar;
    private double speed;
    private double height;
    private double length;
    private final boolean barfBagNeeded;

    public RollerCoaster(double price, double utilityCost, int maxRiders, 
                         int ticketsAvailible, int numberOfAttendants, 
                         int hoursPerDay, double speed, 
                         double height, double length,
                         int numOfCars, int seatsPerCar) {
        super(price, utilityCost, (numOfCars * seatsPerCar), ticketsAvailible, numberOfAttendants, 
              hoursPerDay);
        this.numOfCars = numOfCars;
        this.seatsPerCar = seatsPerCar;
        this.speed = speed;
        this.height = height;
        this.length = length;
        this.barfBagNeeded = this.speed > 10.0; // Example condition for needing a barf bag
    }
    public RollerCoaster() {
        super();
        this.numOfCars = 0;
        this.seatsPerCar = 0;
        this.barfBagNeeded = this.speed > 10.0;
        this.speed = 0;
        this.height = 0;
        this.length = 0;
        this.isRunning = false;
    }
    public int getNumOfCars() {
        return numOfCars;
    }
    public void setNumOfCars(int numOfCars) {
        this.numOfCars = numOfCars;
    }
    public int getSeatsPerCar() {
        return seatsPerCar;
    }
    public void setSeatsPerCar(int seatsPerCar) {
        this.seatsPerCar = seatsPerCar;
    }
    public double getSpeed() {
        return speed;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public double getLength() {
        return length;
    }
    public void setLength(double length) {
        this.length = length;
    }
    public boolean getBarfBagNeeded() {
        return this.barfBagNeeded;
    }
    public boolean isRunning() {
        return this.isRunning && OPEN;
    }
    public Runnable barf = () ->  {
        while(true) {
            int random = (int) (Math.random() * 100);
            if (random < 10) { // 10% chance of barfing
                System.out.println("Someone barfed! Barf bags are needed!");
                break;
            } else {
                System.out.println("No one barfed.");
            }
            try {
                Thread.sleep(1000); // Check every second
            } catch (InterruptedException e) {
                System.out.println("Barf check interrupted.");
            }
        }
    };
    public Runnable startRide = () -> {
        super.checkIfRunning.run();
        // Check if the ride is open
        super.checkIfOpen.run();
        isRunning = true;
        System.out.println("The roller coaster is starting!");
        Thread barfThread = new Thread(barf);
        barfThread.start();
        for (int i = 0; i < numOfCars; i++) {
            final int carNumber = i + 1;
            System.out.println("Car " + carNumber + " is starting!");

            // Create a separate Timer for each car
            Timer carTimer = new Timer();

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Car " + carNumber + " is moving!");
                }
            };

            // Schedule the task to run periodically
            carTimer.schedule(task, 0, (long) (5000 / speed));

            // Schedule a separate task to cancel the TimerTask after length/speed seconds
            carTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Car " + carNumber + " has stopped!");
                    carTimer.cancel();
                }
            }, (int) (length / speed) * 100); // Cancel after length/speed seconds
        }
        barfThread.interrupt();
        isRunning = false;
    };
}
    