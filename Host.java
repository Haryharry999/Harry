package person;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Your Name
 */
public class Host extends Person {
    private List<String> rentalAgreements;
    private List<String> cooperatingOwners;

    public Host(int id, String fullName, String dob, String email, String phone, List<String> rentalAgreements, List<String> cooperatingOwners) {
        super(id, fullName, dob, email, phone);
        this.rentalAgreements = rentalAgreements;
        this.cooperatingOwners = cooperatingOwners;
    }

    // Getters and Setters
    public List<String> getRentalAgreements() { return rentalAgreements; }
    public void setRentalAgreements(List<String> rentalAgreements) { this.rentalAgreements = rentalAgreements; }
    public List<String> getCooperatingOwners() { return cooperatingOwners; }
    public void setCooperatingOwners(List<String> cooperatingOwners) { this.cooperatingOwners = cooperatingOwners; }

    @Override
    public String toString() {
        return "Host{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", dob='" + dob + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", rentalAgreements=" + rentalAgreements +
                ", cooperatingOwners=" + cooperatingOwners +
                '}';
    }
    public static Host findHostByName(String fullName, List<Host> hosts) {
        for (Host host : hosts) {
            if (host.getFullName().equalsIgnoreCase(fullName)) {
                return host;
            }
        }
        return null; // Return null if no host is found with the provided name
    }

    public static Host findHostById(int id, ArrayList<Host> hosts) {
        for (Host host : hosts) {
            if (host.getId() == id) {
                return host;
            }
        }
        return null; // Return null if no host is found with the provided ID
    }
}