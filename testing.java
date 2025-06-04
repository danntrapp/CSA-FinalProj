import Rides.Attraction;
import Rides.LogFlume;
import Rides.MerryGoRound;
import Rides.RollerCoaster;
import java.lang.reflect.Method;
import java.util.ArrayList;
public class testing {
    //god forgive me for this bullshit, this is all pulled out of my ass
    public static void main(String[] args) {
        // Create a new RollerCoaster object
        int numOfCars = 5;
        int seatsPerCar = 4;
        double speed = 10.0; // in meters per second
        double height = 20.0; //in meters
        double length = 500.0; // in meters
        //runs for 5 seconds
        int ticketsAvailable = 300; 
        int hoursPerDay = 8;
        double price = 5.0;
        double utilityCost = 2.0;
        // Create a new RollerCoaster object
        RollerCoaster rc = new RollerCoaster(price, utilityCost, 100, ticketsAvailable, speed, height, length, numOfCars, seatsPerCar);
        RollerCoaster rc2 = new RollerCoaster(price, utilityCost, 100, ticketsAvailable, 
                                              speed, height, length, numOfCars, 5);
        LogFlume lf = new LogFlume(price, utilityCost, 100, ticketsAvailable, numOfCars, speed, height, length, seatsPerCar, 1, 0.5);
        MerryGoRound mg = new MerryGoRound(price, utilityCost, 100, ticketsAvailable, speed, numOfCars, seatsPerCar);
                                             //Im done commenting this, I can't explain this shit anymore
        ArrayList<Attraction> attractions = new ArrayList<>();
        AmusementPark GilroyGardens = new AmusementPark("Gilroy Gardens", "Gilroy", attractions);
        attractions.add(rc);
        attractions.add(rc2);
        attractions.add(lf);
        attractions.add(mg);
        Thread mgThread = new Thread(mg);
        Thread rcThread = new Thread(rc.startRide);
        Thread rc2Thread = new Thread(rc2.startRide);
        Thread lfThread = new Thread(lf.startRide);
        rcThread.start();
        rc2Thread.start();
        lfThread.start();
        mgThread.start();
        try {
        Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(Attraction attraction : attractions) {
            Method[] m = attraction.getClass().getDeclaredMethods();
            for(Method method : m) {
                if(method.getName().startsWith("set")) {
                    System.out.println("Method: " + method.getName());
                    try {
                        if(method.getParameterTypes()[0] == int.class) {
                            method.invoke(attraction, 10);
                        } else if(method.getParameterTypes()[0] == double.class) {
                            method.invoke(attraction, 10.0);
                        } else if(method.getParameterTypes()[0] == boolean.class) {
                            method.invoke(attraction, true);
                        }
                        else if(method.getParameterTypes()[0] == String.class) {
                            method.invoke(attraction, "test");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Changed Values:");
            System.out.println(attraction.toString());
            System.out.println("--------------------------------------------------");
            ArrayList<Method> methods = new ArrayList<>();
            methods.addAll(java.util.Arrays.asList(attraction.getClass().getDeclaredMethods()));
            methods.addAll(java.util.Arrays.asList(attraction.getClass().getSuperclass().getDeclaredMethods()));
            for(Method method : methods) {
                if(method.getName().startsWith("set")) {
                    System.out.println("Method: " + method.getName());
                    try {
                        if(method.getParameterTypes()[0] == int.class) {
                            method.invoke(attraction, 10);
                        } else if(method.getParameterTypes()[0] == double.class) {
                            method.invoke(attraction, 10.0);
                        } else if(method.getParameterTypes()[0] == boolean.class) {
                            method.invoke(attraction, true);
                        }
                        else if(method.getParameterTypes()[0] == String.class) {
                            method.invoke(attraction, "test");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            for(Method method : methods) {
                if(method.getName().startsWith("get")) {
                    System.out.println("Method: " + method.getName());
                    try {
                        System.out.println("Value: " + method.invoke(attraction));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("--------------------------------------------------");
            System.out.println("--------------------------------------------------");
        }
        GilroyGardens.calculateTotalRevenue();
        System.out.println(GilroyGardens.getTotalRevanue());
        


    }
}
