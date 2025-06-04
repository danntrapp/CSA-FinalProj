import Rides.*;
import java.util.ArrayList;
public class AmusementPark {
    private String name;
    private String location;
    private ArrayList<Attraction> attractions;
    private double totalRevenue;
    private double totalUtilityCost;

    public AmusementPark(String name, String location) {
        this.name = name;
        this.location = location;
        this.attractions = new ArrayList<>();
        this.totalRevenue = 0.0;
        this.totalUtilityCost = 0.0;
    }
    public AmusementPark() {
        this.name = "No Name";
        this.location = "Narnia";
        this.attractions = new ArrayList<>();
        this.totalRevenue = 0.0;
        this.totalUtilityCost = 0.0;
    }
    public AmusementPark(String n, String loc, ArrayList<Attraction> attractions) {
        this.name = n;
        this.location = loc;
        this.attractions = attractions;
        this.totalRevenue = 0.0;
        this.totalUtilityCost = 0.0;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public ArrayList<Attraction> getAttractions() {
        return attractions;
    }
    public void setAttractions(ArrayList<Attraction> attractions) {
        this.attractions = attractions;
    }
    public void calculateTotalRevenue() {
        for(Attraction attraction : attractions) {
            this.totalRevenue += attraction.getPrice() * attraction.getTicketsAvailible();
        }
    }
    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
    public double getTotalUtilityCost() {
        return totalUtilityCost;
    }
    public void calculateTotalUtilityCost() {
        for(Attraction attraction : attractions) {
            this.totalUtilityCost += attraction.getUtilityCost();
        }
    }
    public double getTotalRevanue() {
        return totalRevenue;
    }
    @Override
    public String toString() {
        return "AmusementPark: " +
                "name = '" + name + '\'' +
                ", location = '" + location + '\'' +
                ", attractions = " + attractions.toString() +
                ", totalRevenue = " + totalRevenue +
                ", totalUtilityCost = " + totalUtilityCost;
    }
    public void addAttraction(Attraction attraction) {
        this.attractions.add(attraction);
    }
    public void removeAttraction(Attraction attraction) {
        this.attractions.remove(attraction);
    }
    public String getAttractionDetails() {
        StringBuilder details = new StringBuilder();
        for (Attraction attraction : attractions) {
            details.append(attraction.toString()).append("\n");
        }
        return details.toString();
    }
    

    public void setTotalUtilityCost(double totalUtilityCost) {
        this.totalUtilityCost = totalUtilityCost;
    }
    
}
