package property;

/**
 * @author Your Name
 */
public class CommercialProperty extends Property {
    private String businessType;
    private int parkingSpaces;
    private double squareFootage;

    public CommercialProperty(int id, String address, double pricing, String status, int ownerId, String businessType, int parkingSpaces, double squareFootage) {
        super(id, address, pricing, status, ownerId);
        this.businessType = businessType;
        this.parkingSpaces = parkingSpaces;
        this.squareFootage = squareFootage;
    }

    // Getters and Setters
    public String getBusinessType() { return businessType; }
    public void setBusinessType(String businessType) { this.businessType = businessType; }
    public int getParkingSpaces() { return parkingSpaces; }
    public void setParkingSpaces(int parkingSpaces) { this.parkingSpaces = parkingSpaces; }
    public double getSquareFootage() { return squareFootage; }
    public void setSquareFootage(double squareFootage) { this.squareFootage = squareFootage; }

    @Override
    public String toString() {
        return "CommercialProperty{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", pricing=" + pricing +
                ", status='" + status + '\'' +
                ", ownerId=" + ownerId +
                ", businessType='" + businessType + '\'' +
                ", parkingSpaces=" + parkingSpaces +
                ", squareFootage=" + squareFootage +
                '}';
    }
}