package person;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Your Name
 */
public class Owner extends Person {
    private List<String> rentalAgreements;

    public Owner(int id, String fullName, String dob, String email, String phone, List<String> rentalAgreements) {
        super(id, fullName, dob, email, phone);
        this.rentalAgreements = rentalAgreements;
    }

    // Getters and Setters
    public List<String> getRentalAgreements() { return rentalAgreements; }
    public void setRentalAgreements(List<String> rentalAgreements) { this.rentalAgreements = rentalAgreements; }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", dob='" + dob + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", rentalAgreements=" + rentalAgreements +
                '}';
    }
    public static Owner findOwnerByName(String fullName, List<Owner> owners) {
        for (Owner owner : owners) {
            if (owner.getFullName().equalsIgnoreCase(fullName)) {
                return owner;
            }
        }
        return null; // Return null if no owner is found with the provided name
    }

    public static Owner findOwnerById(int id, ArrayList<Owner> owners) {
        for (Owner owner : owners) {
            if (owner.getId() == id) {
                return owner;
            }
        }
        return null; // Return null if no owner is found with the provided ID
    }
}