package person;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Your Name
 */
public class Tenant extends Person {
    private List<String> rentalAgreements;

    public Tenant(int id, String fullName, String dob, String email, String phone, List<String> rentalAgreements) {
        super(id, fullName, dob, email, phone);
        this.rentalAgreements = rentalAgreements;
    }

    // Getters and Setters
    public List<String> getRentalAgreements() { return rentalAgreements; }
    public void setRentalAgreements(List<String> rentalAgreements) { this.rentalAgreements = rentalAgreements; }

    @Override
    public String toString() {
        return "Tenant{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", dob='" + dob + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", rentalAgreements=" + rentalAgreements +
                '}';
    }
    public static Tenant findTenantByName(String fullName, ArrayList<Tenant> tenants) {
        for (Tenant tenant : tenants) {
            if (tenant.getFullName().equalsIgnoreCase(fullName)) {
                return tenant;
            }
        }
        return null; // Return null if no tenant is found with the provided name
    }

    // Method to find a Tenant by ID
    public static Tenant findTenantById(int id, ArrayList<Tenant> tenants) {
        for (Tenant tenant : tenants) {
            if (tenant.getId() == id) {
                return tenant;
            }
        }
        return null; // Return null if no tenant is found with the provided ID
    }
}