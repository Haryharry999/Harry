package property;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Your Name
 */
public abstract class Property {
    protected int id;
    protected String address;
    protected double pricing;
    protected String status;
    protected int ownerId;

    public Property(int id, String address, double pricing, String status, int ownerId) {
        this.id = id;
        this.address = address;
        this.pricing = pricing;
        this.status = status;
        this.ownerId = ownerId;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public double getPricing() { return pricing; }
    public void setPricing(double pricing) { this.pricing = pricing; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getOwnerId() { return ownerId; }
    public void setOwnerId(int ownerId) { this.ownerId = ownerId; }

    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", pricing=" + pricing +
                ", status='" + status + '\'' +
                ", ownerId=" + ownerId +
                '}';
    }
    public static Property findPropertyByAddress(String address, ArrayList<Property> properties) {
        for (Property property : properties) {
            if (property.getAddress().equalsIgnoreCase(address)) {
                return property;
            }
        }
        return null; // Return null if no property is found with the provided address
    }

    public static Property findPropertyById(int id, List<Property> properties) {
        for (Property property : properties) {
            if (property.getId() == id) {
                return property;
            }
        }
        return null; // Return null if no property is found with the provided ID
    }
}