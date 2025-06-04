package Rides;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
public class RollerCoaster extends Attraction {
    private int numOfCars;
    private int seatsPerCar;
    private double speed;
    private double height;
    private double length;
    private boolean barfBagNeeded;

    public RollerCoaster(double price, double utilityCost, int maxRiders, 
                         int ticketsAvailible, double speed, 
                         double height, double length,
                         int numOfCars, int seatsPerCar) {
        super(price, utilityCost, (numOfCars * seatsPerCar), ticketsAvailible);
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
    public void setBarfBagNeeded(boolean bbNeeded) {
        this.barfBagNeeded = bbNeeded;
    }
    public boolean isRunning() {
        return this.isRunning && OPEN;
    }
    public Runnable barf = () ->  {
        while (super.isRunning) {
            int random = (int) (Math.random() * 100);
            if (random < 10) {
                System.out.println("Someone barfed! Barf bags are needed!");
                break;
            } else {
                System.out.println("No one barfed.");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Barf check interrupted.");
                break;
            }
        }
    };
    public Runnable startRide = () -> {
        super.checkIfRunning.run();
        super.checkIfOpen.run();
        super.isRunning = true;
        System.out.println("The roller coaster is starting!");

        Thread barfThread = new Thread(this.barf);
        barfThread.start();

        reduceTicketsAvailible();

        CountDownLatch latch = new CountDownLatch(numOfCars);

        for (int i = 0; i < numOfCars; i++) {
            final int carNumber = i + 1;
            System.out.println("Car " + carNumber + " is starting!");

            Timer carTimer = new Timer();

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Car " + carNumber + " is moving!");
                }
            };

            carTimer.schedule(task, 0, (long) (5000 / speed));

            carTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Car " + carNumber + " has stopped!");
                    carTimer.cancel();
                    carTimer.purge();
                    latch.countDown(); // Signal this car is done
                }
            }, (int) (length / speed) * 100);
        }

        try {
            latch.await(); // Wait for all cars to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        barfThread.interrupt(); // Now stop the barf check
        super.isRunning = false;
        System.out.println("The roller coaster ride has ended!");
    };
}
