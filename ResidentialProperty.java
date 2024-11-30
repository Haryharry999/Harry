package property;

/**
 * @author Your Name
 */
public class ResidentialProperty extends Property {
    private int bedrooms;
    private boolean garden;
    private boolean petFriendly;

    public ResidentialProperty(int id, String address, double pricing, String status, int ownerId, int bedrooms, boolean garden, boolean petFriendly) {
        super(id, address, pricing, status, ownerId);
        this.bedrooms = bedrooms;
        this.garden = garden;
        this.petFriendly = petFriendly;
    }

    // Getters and Setters
    public int getBedrooms() { return bedrooms; }
    public void setBedrooms(int bedrooms) { this.bedrooms = bedrooms; }
    public boolean isGarden() { return garden; }
    public void setGarden(boolean garden) { this.garden = garden; }
    public boolean isPetFriendly() { return petFriendly; }
    public void setPetFriendly(boolean petFriendly) { this.petFriendly = petFriendly; }

    @Override
    public String toString() {
        return "ResidentialProperty{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", pricing=" + pricing +
                ", status='" + status + '\'' +
                ", ownerId=" + ownerId +
                ", bedrooms=" + bedrooms +
                ", garden=" + garden +
                ", petFriendly=" + petFriendly +
                '}';
    }
}