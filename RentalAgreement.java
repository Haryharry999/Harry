package rentalagreement;

import person.Host;
import person.Owner;
import person.Tenant;
import property.Property;

import java.util.ArrayList;
import java.util.Date;

/**
 * Rental Agreement representation
 * Author: [Your Name Here]
 */
public class RentalAgreement {
    private int id;
    private Tenant mainTenant;
    private ArrayList<Tenant> subTenants;
    private ArrayList<Host> hosts;
    private Owner owner;
    private Property property;
    private String period;
    private Date contractDate;
    private double rentingFee;
    private String status;

    public RentalAgreement(int id, Tenant mainTenant, ArrayList<Tenant> subTenants, ArrayList<Host> hosts, Owner owner, Property property, String period, Date contractDate, double rentingFee, String status) {
        this.id = id;
        this.mainTenant = mainTenant;
        this.subTenants = subTenants;
        this.hosts = hosts;
        this.owner = owner;
        this.property = property;
        this.period = period;
        this.contractDate = contractDate;
        this.rentingFee = rentingFee;
        this.status = status;
    }

    // Getters and Setters for all fields

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Tenant getMainTenant() { return mainTenant; }
    public void setMainTenant(Tenant mainTenant) { this.mainTenant = mainTenant; }

    public ArrayList<Tenant> getSubTenants() { return subTenants; }
    public void setSubTenants(ArrayList<Tenant> subTenants) { this.subTenants = subTenants; }

    public ArrayList<Host> getHosts() { return hosts; }
    public void setHosts(ArrayList<Host> hosts) { this.hosts = hosts; }

    public Owner getOwner() { return owner; }
    public void setOwner(Owner owner) { this.owner = owner; }

    public Property getProperty() { return property; }
    public void setProperty(Property property) { this.property = property; }

    public String getPeriod() { return period; }
    public void setPeriod(String period) { this.period = period; }

    public Date getContractDate() { return contractDate; }
    public void setContractDate(Date contractDate) { this.contractDate = contractDate; }

    public double getRentingFee() { return rentingFee; }
    public void setRentingFee(double rentingFee) { this.rentingFee = rentingFee; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public static RentalAgreement findRentalAgreementById(int id, ArrayList<RentalAgreement> rentalAgreements) {
        for (RentalAgreement rentalAgreement : rentalAgreements) {
            if (rentalAgreement.getId() == id) {
                return rentalAgreement;
            }
        }
        return null;
    }
    @Override
    public String toString() {
        return "RentalAgreement{" +
                "id=" + id +
                ", mainTenant=" + mainTenant +
                ", subTenants=" + subTenants +
                ", hosts=" + hosts +
                ", owner=" + owner +
                ", property=" + property +
                ", period='" + period + '\'' +
                ", contractDate=" + contractDate +
                ", rentingFee=" + rentingFee +
                ", status='" + status + '\'' +
                '}';
    }
}