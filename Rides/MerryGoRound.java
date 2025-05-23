package Rides;

public class MerryGoRound extends Attraction implements Runnable {
    private int numOfHorses;
    private int ridersPerHorse;
    private double speed; // in radians per second
    private boolean isRunning = false;

    public MerryGoRound(double price, double utilityCost, int maxRiders, 
                        int ticketsAvailable, int numberOfAttendants, 
                        int hoursPerDay, double speed, int numOfHorses, int ridersPerHorse) {
        super(price, utilityCost, (numOfHorses * ridersPerHorse), ticketsAvailable, numberOfAttendants, hoursPerDay);
        this.numOfHorses = numOfHorses;
        this.speed = speed;
        this.isRunning = false;
    }

    public MerryGoRound() {
        super();
        this.numOfHorses = 0;
        this.speed = 0;
        this.isRunning = false;
    }

    public int getNumOfHorses() {
        return numOfHorses;
    }

    public void setNumOfHorses(int numOfHorses) {
        this.numOfHorses = numOfHorses;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public int getRidersPerHorse() {
        return ridersPerHorse;
    }
    public void setRidersPerHorse(int ridersPerHorse) {
        this.ridersPerHorse = ridersPerHorse;
    }
    public boolean isRunning() {
        return isRunning;
    }
    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
    @Override
    public void run() {
        super.checkIfOpen.run();
        super.checkIfRunning.run();
        
            this.setRunning(true);
            System.out.println("Merry-Go-Round is now running at speed: " + speed + " radians/second.");
            for(int i = 0; i < 10; i++) {
                System.out.println("Merry-Go-Round is spinning... " + (i + 1) + " rotations.");
                try {
                    Thread.sleep(1000); // Simulate the ride duration
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.setRunning(false);
            System.out.println("Merry-Go-Round has stopped.");
        }
    };
    
