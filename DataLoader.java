package feature;
import person.*;
import property.*;
import rentalagreement.*;
import payment.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Integer.parseInt;


public class DataLoader {

    public static ArrayList<Tenant> loadTenants(String filename) throws IOException {
        ArrayList<Tenant> tenants = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("data/" + filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            int id = parseInt(parts[0]);
            String fullName = parts[1];
            String dob = parts[2];
            String email = parts[3];
            String phone = parts[4];
            List<String> rentalAgreements = Arrays.asList(parts[5].split(";"));
            tenants.add(new Tenant(id, fullName, dob, email, phone, rentalAgreements));
        }
        reader.close();
        return tenants;
    }

    public static ArrayList<Owner> loadOwners(String filename) throws IOException {
        ArrayList<Owner> owners = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("data/" + filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            String fullName = parts[1];
            String dob = parts[2];
            String email = parts[3];
            String phone = parts[4];
            List<String> rentalAgreements = Arrays.asList(parts[5].split(";"));
            owners.add(new Owner(id, fullName, dob, email, phone, rentalAgreements));
        }
        reader.close();
        return owners;
    }

    public static ArrayList<Host> loadHosts(String filename) throws IOException {
        ArrayList<Host> hosts = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("data/" + filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            int id = parseInt(parts[0]);
            String fullName = parts[1];
            String dob = parts[2];
            String email = parts[3];
            String phone = parts[4];
            List<String> rentalAgreements = Arrays.asList(parts[5].split(";"));
            List<String> cooperatingOwners = Arrays.asList(parts[6].split(";"));
            hosts.add(new Host(id, fullName, dob, email, phone, rentalAgreements, cooperatingOwners));
        }
        reader.close();
        return hosts;
    }

    public static ArrayList<Property> loadProperties(String filename) throws IOException {
        ArrayList<Property> properties = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("data/" + filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String type = parts[0];
            int id = parseInt(parts[1]);
            String address = parts[2];
            double pricing = Double.parseDouble(parts[3]);
            String status = parts[4];
            int ownerId = parseInt(parts[5]);

            if (type.equals("Residential")) {
                int bedrooms = parseInt(parts[6]);
                boolean garden = Boolean.parseBoolean(parts[7]);
                boolean petFriendly = Boolean.parseBoolean(parts[8]);
                properties.add(new ResidentialProperty(id, address, pricing, status, ownerId, bedrooms, garden, petFriendly));
            } else if (type.equals("Commercial")) {
                String businessType = parts[6];
                int parkingSpaces = parseInt(parts[7]);
                double squareFootage = Double.parseDouble(parts[8]);
                properties.add(new CommercialProperty(id, address, pricing, status, ownerId, businessType, parkingSpaces, squareFootage));
            }
        }
        reader.close();
        return properties;
    }

    public static ArrayList<RentalAgreement> loadRentalAgreements(String filename) throws IOException {
        ArrayList<RentalAgreement> rentalAgreements = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("data/" + filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length != 10) {
                    System.err.println("Incomplete data for line (found " + parts.length + " parts): " + line);
                    continue;
                }

                int id = Integer.parseInt(parts[0].trim());
                String mainTenantString = parts[1].trim();
                Tenant mainTenant = Tenant.findTenantByName(mainTenantString, UISystem.tenants);

                String subTenantString = parts[2].trim();
                ArrayList<Tenant> subTenant = new ArrayList<>();
                for (String subTenantName : subTenantString.split(";")) {
                    Tenant subTenantTemp = Tenant.findTenantByName(subTenantName, UISystem.tenants);
                    if (subTenantTemp != null) {
                        subTenant.add(subTenantTemp);
                    } else {
                        System.err.println("Sub-tenant not found: " + subTenantName);
                    }
                }

                String hostString = parts[3].trim();
                ArrayList<Host> host = new ArrayList<>();
                for (String hostName : hostString.split(";")) {
                    Host hostTemp = Host.findHostByName(hostName, UISystem.hosts);
                    if (hostTemp != null) {
                        host.add(hostTemp);
                    } else {
                        System.err.println("Host not found: " + hostName);
                    }
                }

                String ownerString = parts[4].trim();
                Owner owner = Owner.findOwnerByName(ownerString, UISystem.owners);

                String propertyAddress = parts[5].trim();
                Property property = Property.findPropertyByAddress(propertyAddress, UISystem.properties);

                String period = parts[6].trim();
                Date contractDate = new SimpleDateFormat("dd/MM/yyyy").parse(parts[7].trim());

                Double rentingFee = tryParseDouble(parts[8].trim());
                if (rentingFee == null) {
                    System.err.println("Invalid renting fee for line: " + line);
                    continue;
                }

                String status = parts[9].trim();

                rentalAgreements.add(new RentalAgreement(id, mainTenant, subTenant, host, owner, property, period, contractDate, rentingFee, status));
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return rentalAgreements;
    }
    private static Integer tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null; // Return null if the string can't be parsed to an integer
        }
    }

    private static Double tryParseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return null; // Return null if the string can't be parsed to a double
        }
    }

    public static ArrayList<Payment> loadPayments(String filename) throws IOException {
        ArrayList<Payment> payments = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("data/" + filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            int id = parseInt(parts[0]);
            String tenantName = parts[1];
            double amount = Double.parseDouble(parts[2]);
            String date = parts[3];
            String method = parts[4];
            payments.add(new Payment(id, tenantName, amount, date, method));
        }
        reader.close();
        return payments;
    }
}
