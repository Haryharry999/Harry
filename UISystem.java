package feature;

import person.*;
import property.*;
import rentalagreement.*;
import payment.*;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UISystem {
    private RentalManager rentalManager = new RentalManagerImpl();
    public static ArrayList<Tenant> tenants;
    public static ArrayList<Owner> owners;
    public static ArrayList<Host> hosts;
    public static ArrayList<Property> properties;
    public static ArrayList<Payment> payments;
    private final Scanner scanner = new Scanner(System.in);
    private static ArrayList<RentalAgreement> rentalAgreements;

    public void loadData() throws IOException {
        tenants = DataLoader.loadTenants("tenants.txt");
        owners = DataLoader.loadOwners("owners.txt");
        hosts = DataLoader.loadHosts("hosts.txt");
        properties = DataLoader.loadProperties("properties.txt");
        payments = DataLoader.loadPayments("payments.txt");
        rentalManager = new RentalManagerImpl();
        rentalAgreements = DataLoader.loadRentalAgreements("rental_agreements.txt");
        for (RentalAgreement agreement : rentalAgreements) {
            rentalManager.addRentalAgreement(agreement);
        }
    }

    public void displayMenu() {
        System.out.println("Rental Manager App");
        System.out.println("1. View Tenants' Information");
        System.out.println("2. View Owners' Information");
        System.out.println("3. View Hosts' Information");
        System.out.println("4. View Properties' Information");
        System.out.println("5. Rental Manager Menu");
        System.out.println("6. Exit");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewTenants(scanner);
                    break;
                case 2:
                    viewOwners(scanner);
                    break;
                case 3:
                    viewHosts(scanner);
                    break;
                case 4:
                    viewProperties(scanner);
                    break;
                case 5:
                    rentalMangerView();
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void rentalMangerView(){
        System.out.println("1. Add Rental Agreement");
        System.out.println("2. Update Rental Agreement");
        System.out.println("3. Delete Rental Agreement");
        System.out.println("4. Get All Rental Agreements");
        System.out.println("5. Get Rental Agreements by Owner Name");
        System.out.println("6. Get Rental Agreements by Property Address");
        System.out.println("7. Get Rental Agreements by Status");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newli
        switch (choice){
            case 1:
                addRentalAgreement(scanner);
                break;
            case 2:
                updateRentalAgreement(scanner);
                break;
            case 3:
                deleteRentalAgreement(scanner);
                break;
            case 4:
                getAllRentalAgreements(scanner);
                break;
            case 5:
                getRentalAgreementsByOwnerName(scanner);
                break;
            case 6:
                getRentalAgreementsByPropertyAddress(scanner);
                break;
            case 7:
                getRentalAgreementsByStatus(scanner);
                break;
            case 8:
                System.out.println("Exiting...");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void viewTenants(Scanner scanner) {
        System.out.println("View Tenants");
        System.out.println("1. Sort by ID");
        System.out.println("2. Sort by Name");
        System.out.println("3. Return to Menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newlin

        switch (choice) {
            case 1:
                tenants.sort((t1, t2) -> Integer.compare(t1.getId(), t2.getId()));
                break;
            case 2:
                tenants.sort((t1, t2) -> t1.getFullName().compareTo(t2.getFullName()));
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice. Returning to menu.");
                return;
        }

        System.out.println("ID    Full Name           Date of Birth  Contact Info        Rental Agreements");
        for (Tenant tenant : tenants) {
            System.out.printf("%-5d %-20s %-15s %-20s %s%n", tenant.getId(), tenant.getFullName(), tenant.getDob(), tenant.getEmail(), String.join(", ", tenant.getRentalAgreements()));
        }

        System.out.print("Do you want to save this report? (yes/no): ");
        String saveReport = scanner.nextLine();
        if (saveReport.equalsIgnoreCase("yes")) {
            System.out.print("Enter the name for the report file: ");
            String filename = scanner.nextLine();
            try {
                ReportGenerator.generateReport(rentalManager.getAllRentalAgreements(), filename);
                System.out.println("Report saved successfully.");
            } catch (IOException e) {
                System.out.println("Error saving report: " + e.getMessage());
            }
        }
    }

    private void viewOwners(Scanner scanner) {
        System.out.println("View Owners");
        System.out.println("1. Sort by ID");
        System.out.println("2. Sort by Name");
        System.out.println("3. Return to Menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                owners.sort((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
                break;
            case 2:
                owners.sort((o1, o2) -> o1.getFullName().compareTo(o2.getFullName()));
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice. Returning to menu.");
                return;
        }

        System.out.println("ID    Full Name           Date of Birth  Contact Info        Rental Agreements");
        for (Owner owner : owners) {
            System.out.printf("%-5d %-20s %-15s %-20s %s%n", owner.getId(), owner.getFullName(), owner.getDob(), owner.getEmail(), String.join(", ", owner.getRentalAgreements()));
        }

        System.out.print("Do you want to save this report? (yes/no): ");
        String saveReport = scanner.nextLine();
        if (saveReport.equalsIgnoreCase("yes")) {
            System.out.print("Enter the name for the report file: ");
            String filename = scanner.nextLine();
            try {
                ReportGenerator.generateReport(rentalManager.getAllRentalAgreements(), filename);
                System.out.println("Report saved successfully.");
            } catch (IOException e) {
                System.out.println("Error saving report: " + e.getMessage());
            }
        }
    }

    private void viewHosts(Scanner scanner) {
        System.out.println("View Hosts");
        System.out.println("1. Sort by ID");
        System.out.println("2. Sort by Name");
        System.out.println("3. Return to Menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                hosts.sort((h1, h2) -> Integer.compare(h1.getId(), h2.getId()));
                break;
            case 2:
                hosts.sort((h1, h2) -> h1.getFullName().compareTo(h2.getFullName()));
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice. Returning to menu.");
                return;
        }

        System.out.println("ID    Full Name           Date of Birth  Contact Info        Rental Agreements        Cooperating Owners");
        for (Host host : hosts) {
            System.out.printf("%-5d %-20s %-15s %-20s %-25s %s%n", host.getId(), host.getFullName(), host.getDob(), host.getEmail(), String.join(", ", host.getRentalAgreements()), String.join(", ", host.getCooperatingOwners()));
        }

        System.out.print("Do you want to save this report? (yes/no): ");
        String saveReport = scanner.nextLine();
        if (saveReport.equalsIgnoreCase("yes")) {
            System.out.print("Enter the name for the report file: ");
            String filename = scanner.nextLine();
            try {
                ReportGenerator.generateReport(rentalManager.getAllRentalAgreements(), filename);
                System.out.println("Report saved successfully.");
            } catch (IOException e) {
                System.out.println("Error saving report: " + e.getMessage());
            }
        }
    }

    private void viewProperties(Scanner scanner) {
        System.out.println("View Properties");
        System.out.println("1. Sort by ID");
        System.out.println("2. Sort by Address");
        System.out.println("3. Return to Menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                properties.sort((p1, p2) -> Integer.compare(p1.getId(), p2.getId()));
                break;
            case 2:
                properties.sort((p1, p2) -> p1.getAddress().compareTo(p2.getAddress()));
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice. Returning to menu.");
                return;
        }

        System.out.println("ID    Address             Pricing   Status          Owner ID  Type");
        for (Property property : properties) {
            System.out.printf("%-5d %-20s %-10.2f %-15s %-8d %s%n", property.getId(), property.getAddress(), property.getPricing(), property.getStatus(), property.getOwnerId(), property instanceof ResidentialProperty ? "Residential" : "Commercial");
        }

        System.out.print("Do you want to save this report? (yes/no): ");
        String saveReport = scanner.nextLine();
        if (saveReport.equalsIgnoreCase("yes")) {
            System.out.print("Enter the name for the report file: ");
            String filename = scanner.nextLine();
            try {
                ReportGenerator.generateReport(rentalManager.getAllRentalAgreements(), filename);
                System.out.println("Report saved successfully.");
            } catch (IOException e) {
                System.out.println("Error saving report: " + e.getMessage());
            }
        }
    }


    private void addRentalAgreement(Scanner scanner) {
        System.out.print("Enter new Rental Agreement ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Display and select main tenant
        Tenant mainTenant = selectTenant(scanner);

        // Collect sub-tenant IDs
        ArrayList<Tenant> subTenants = selectSubTenants(scanner);

        // Select property
        Property selectedProperty = selectProperty(scanner);

        // Select hosts
        ArrayList<Host> selectedHosts = selectHosts(scanner);

        // Collect other details
        System.out.print("Enter Owner ID: ");
        int ownerId = Integer.parseInt(scanner.nextLine().trim());
        Owner owner = Owner.findOwnerById(ownerId, owners);

        System.out.print("Enter Period (e.g., MONTHLY): ");
        String period = scanner.nextLine().trim();

        // Create and parse contract date
        Date contractDate = null;
        while (contractDate == null) {
            System.out.print("Enter Contract Date (dd/MM/yyyy): ");
            String dateInput = scanner.nextLine().trim();
            try {
                contractDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateInput);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }

        System.out.print("Enter Renting Fee: ");
        double rentingFee = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Enter Status (NEW, ACTIVE, COMPLETED): ");
        String status = scanner.nextLine().trim();

        // Create and add the rental agreement
        RentalAgreement agreement = new RentalAgreement(
                id,
                mainTenant,
                subTenants,
                selectedHosts,
                owner,
                selectedProperty,
                period,
                contractDate,
                rentingFee,
                status
        );

        rentalManager.addRentalAgreement(agreement);
        System.out.println("Rental Agreement added successfully.");

        // Save the new rental agreement to the file
        saveRentalAgreementToFile(agreement);
    }

    private Tenant selectTenant(Scanner scanner) {
        Tenant tenant = null;
        while (tenant == null) {
            Object tenantType = new Object();
            System.out.printf("Available %s Tenants:%n", tenantType);
            for (Tenant t : tenants) {
                System.out.printf("%-5d %-20s %-15s %-20s %s%n",
                        t.getId(), t.getFullName(), t.getDob(), t.getEmail(),
                        String.join(", ", t.getRentalAgreements()));
            }
            System.out.printf("Enter %s Tenant ID: ", tenantType);
            try {
                int tenantId = Integer.parseInt(scanner.nextLine().trim());
                tenant = Tenant.findTenantById(tenantId, tenants);
                if (tenant == null) {
                    System.out.println("Invalid Tenant ID. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric ID.");
            }
        }
        return tenant;
    }

    private ArrayList<Tenant> selectSubTenants(Scanner scanner) {
        ArrayList<Tenant> subTenants = new ArrayList<>();
        System.out.print("Enter number of sub-tenants: ");
        int numSubTenants = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < numSubTenants; i++) {
            subTenants.add(selectTenant(scanner));
        }
        return subTenants;
    }

    private Property selectProperty(Scanner scanner) {
        Property selectedProperty = null;
        while (selectedProperty == null) {
            System.out.println("Available Properties:");
            for (Property property : properties) {
                System.out.printf("%-5d %-20s %-10.2f %-15s %-8d %s%n",
                        property.getId(), property.getAddress(), property.getPricing(), property.getStatus(),
                        property.getOwnerId(), property instanceof ResidentialProperty ? "Residential" : "Commercial");
            }
            System.out.print("Enter Property ID: ");
            try {
                int propertyId = Integer.parseInt(scanner.nextLine().trim());
                selectedProperty = properties.stream().filter(p -> p.getId() == propertyId).findFirst().orElse(null);

                if (selectedProperty == null) {
                    System.out.println("Invalid Property ID. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric ID.");
            }
        }
        return selectedProperty;
    }

    private ArrayList<Host> selectHosts(Scanner scanner) {
        ArrayList<Host> selectedHosts = new ArrayList<>();
        while (true) {
            System.out.println("Available Hosts:");
            for (Host host : hosts) {
                System.out.printf("%-5d %-20s %-15s %-20s %-25s %s%n",
                        host.getId(), host.getFullName(), host.getDob(), host.getEmail(),
                        String.join(", ", host.getRentalAgreements()), String.join(", ", host.getCooperatingOwners()));
            }
            System.out.print("Enter Host ID (or 'done' to finish): ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            try {
                int hostId = Integer.parseInt(input);
                Host host = hosts.stream().filter(h -> h.getId() == hostId).findFirst().orElse(null);
                if (host != null) {
                    selectedHosts.add(host);
                } else {
                    System.out.println("Invalid Host ID. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric ID.");
            }
        }
        return selectedHosts;
    }

    private void saveRentalAgreementToFile(RentalAgreement agreement) {
        try (FileWriter writer = new FileWriter("rental_agreements.txt", true)) {
            writer.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%.2f,%s%n",
                    agreement.getId(),
                    agreement.getMainTenant().getFullName(),
                    agreement.getSubTenants() != null ? agreement.getSubTenants() : "",
                    agreement.getHosts(),
                    agreement.getOwner(),
                    agreement.getProperty(),
                    agreement.getPeriod(),
                    agreement.getContractDate(),
                    agreement.getRentingFee(),
                    agreement.getStatus()));
            System.out.println("Rental Agreement saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving rental agreement to file: " + e.getMessage());
        }
    }
    private void updateRentalAgreement(Scanner scanner) {
        System.out.println("Available Rental Agreements:");
        for (RentalAgreement agreement : rentalManager.getAllRentalAgreements()) {
            System.out.printf("%-5s %-20s %-20s %-20s %-20s %-10s %-10s %-15s %-10.2f %-10s%n",
                    agreement.getId(),
                    agreement.getMainTenant().getFullName(),
                    getSubTenantNames(agreement),
                    getHostNames(agreement),
                    agreement.getOwner(),
                    agreement.getProperty().getAddress(),
                    agreement.getPeriod(),
                    agreement.getContractDate().toString(),
                    agreement.getRentingFee(),
                    agreement.getStatus());
        }
        System.out.print("Enter Rental Agreement ID to update: ");
        int id = scanner.nextInt();

        RentalAgreement agreement = RentalAgreement.findRentalAgreementById(id, rentalAgreements);

        System.out.println("1. Update Main Tenant");
        System.out.println("2. Update Sub-Tenants");
        System.out.println("3. Update Host");
        System.out.println("4. Update Property");
        System.out.println("5. Update Renting Fee");
        System.out.println("6. Update Period");
        System.out.println("7. Update Status");
        System.out.println("8. Return to Menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                agreement.setMainTenant(selectTenant(scanner));
                break;
            case 2:
                agreement.setSubTenants(selectSubTenants(scanner));
                break;
            case 3:
                agreement.setHosts(selectHosts(scanner));
                break;
            case 4:
                agreement.setProperty(selectProperty(scanner));
                break;
            case 5:
                System.out.print("Enter new Renting Fee: ");
                double rentingFee = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                agreement.setRentingFee(rentingFee);
                break;

            case 6:
                System.out.print("Enter new Period: ");
                String period = scanner.nextLine();
                agreement.setPeriod(period);
                break;
            case 7:
                System.out.print("Enter new Status (NEW, ACTIVE, COMPLETED): ");
                String status = scanner.nextLine();
                agreement.setStatus(status);
                break;
            case 8:
                return;
            default:
                System.out.println("Invalid choice. Returning to menu.");
                return;
        }

        rentalManager.updateRentalAgreement(id, agreement);
        System.out.println("Rental Agreement updated successfully.");
    }

    private void deleteRentalAgreement(Scanner scanner) {
        System.out.println("Available Rental Agreements:");
        for (RentalAgreement agreement : rentalManager.getAllRentalAgreements()) {
            System.out.printf("%-5s %-20s %-20s %-20s %-20s %-20s %-10s %-15s %-10.2f %-10s%n", agreement.getId(), agreement.getMainTenant(), agreement.getSubTenants(), agreement.getHosts(), agreement.getOwner(), agreement.getProperty().getAddress(), agreement.getPeriod(), agreement.getContractDate(), agreement.getRentingFee(), agreement.getStatus());
        }
       System.out.print("Enter Rental Agreement ID to delete: ");
        int id = scanner.nextInt();

        rentalManager.deleteRentalAgreement(id);
        System.out.println("Rental Agreement deleted successfully.");
    }

    private void getAllRentalAgreements(Scanner scanner) {
        System.out.println("Get All Rental Agreements");
        System.out.println("1. Sort by ID (Ascending)");
        System.out.println("2. Sort by Contract Date (Earliest to Latest)");
        System.out.println("3. Sort by Status (Active, Complete, New)");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        List<RentalAgreement> agreements = rentalManager.getAllRentalAgreements();
        switch (choice) {
            case 1:
                Sorter.sortById(agreements);
                break;
            case 2:
                Sorter.sortByContractDate(agreements);
                break;
            case 3:
                Sorter.sortByStatus(agreements);
                break;
            default:
                System.out.println("Invalid choice. Returning to menu.");
                return;
        }

        System.out.println("ID    Main Tenant         Sub Tenants       Host              Owner             Property address  Period   Contract Date  Renting Fee  Status");
        for (RentalAgreement agreement : agreements) {
            String subTenants = getSubTenantNames(agreement);
            String hostName = getHostNames(agreement);

            System.out.printf("%-5s %-20s %-20s %-20s %-20s %-25s %-10s %-15s %-10.2f %-10s%n",
                    agreement.getId(),
                    agreement.getMainTenant().getFullName(),
                    subTenants,
                    hostName,
                    agreement.getOwner(),
                    agreement.getProperty().getAddress(),
                    agreement.getPeriod(),
                    agreement.getContractDate(),
                    agreement.getRentingFee(),
                    agreement.getStatus());
        }

        System.out.print("Do you want to save this report? (yes/no): ");
        String saveReport = scanner.nextLine();
        if (saveReport.equalsIgnoreCase("yes")) {
            System.out.print("Enter the name for the report file: ");
            String filename = scanner.nextLine();
            try {
                ReportGenerator.generateReport(agreements, filename);
                System.out.println("Report saved successfully.");
            } catch (IOException e) {
                System.out.println("Error saving report: " + e.getMessage());
            }
        }
    }

    // Helper function to get sub-tenant names
    private String getSubTenantNames(RentalAgreement agreement) {
        ArrayList<Tenant> subTenants = agreement.getSubTenants(); // assuming this method exists and returns a List
        StringBuilder names = new StringBuilder();
        for (Tenant tenant : subTenants) {
            if (!names.isEmpty()) {
                names.append(", ");
            }
            names.append(tenant.getFullName());
        }
        return names.toString();
    }

    private String getHostNames(RentalAgreement agreement) {
        ArrayList<Host> hosts = agreement.getHosts(); // assuming this method exists and returns a List
        StringBuilder names = new StringBuilder();
        for (Host host : hosts) {
            if (!names.isEmpty()) {
                names.append(", ");
            }
            names.append(host.getFullName());
        }
        return names.toString();
    }

    private void getRentalAgreementsByOwnerName(Scanner scanner) {
        System.out.println("Available Owners:");
        for (Owner owner : owners) {
            System.out.printf("%-5d %-20s %-15s %-20s %s%n", owner.getId(), owner.getFullName(), owner.getDob(), owner.getEmail(), String.join(", ", owner.getRentalAgreements()));
        }
        System.out.print("Enter Owner ID: ");
        int ownerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Owner owner = owners.stream().filter(o -> o.getId() == ownerId).findFirst().orElse(null);
        if (owner == null) {
            System.out.println("Owner not found.");
            return;
        }

        List<RentalAgreement> agreements = rentalManager.getRentalAgreementsByOwnerName(owner.getFullName());
        System.out.println("ID    Main Tenant         Sub Tenants       Host              Owner             Property Address  Period   Contract Date  Renting Fee  Status");
        for (RentalAgreement agreement : agreements) {
            System.out.printf("%-5s %-20s %-20s %-20s %-20s %-20s %-20s %-15s %-10.2f %-10s%n", agreement.getId(), agreement.getMainTenant(), agreement.getSubTenants(), agreement.getHosts(), agreement.getOwner().getFullName(), agreement.getProperty().getAddress(), agreement.getPeriod(), agreement.getContractDate(), agreement.getRentingFee(), agreement.getStatus());
        }

        System.out.print("Do you want to save this report? (yes/no): ");
        String saveReport = scanner.nextLine();
        if (saveReport.equalsIgnoreCase("yes")) {
            System.out.print("Enter the name for the report file: ");
            String filename = scanner.nextLine();
            try {
                ReportGenerator.generateReport(agreements, filename);
                System.out.println("Report saved successfully.");
            } catch (IOException e) {
                System.out.println("Error saving report: " + e.getMessage());
            }
        }
    }

    private void getRentalAgreementsByPropertyAddress(Scanner scanner) {
        System.out.println("Available Properties:");
        for (Property property : properties) {
            System.out.printf("%-5d %-20s %-10.2f %-15s %-8d %s%n", property.getId(), property.getAddress(), property.getPricing(), property.getStatus(), property.getOwnerId(), property instanceof ResidentialProperty ? "Residential" : "Commercial");
        }
        System.out.print("Enter Property ID: ");
        int propertyId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Property property = properties.stream().filter(p -> p.getId() == propertyId).findFirst().orElse(null);
        if (property == null) {
            System.out.println("Property not found.");
            return;
        }

        List<RentalAgreement> agreements = rentalManager.getRentalAgreementsByPropertyAddress(String.valueOf(propertyId));
        System.out.println("ID    Main Tenant         Sub Tenants       Host              Owner             Property Address  Period   Contract Date  Renting Fee  Status");
        for (RentalAgreement agreement : agreements) {
            System.out.printf("%-5s %-20s %-20s %-20s %-20s %-20s %-10s %-15s %-10.2f %-10s%n", agreement.getId(), agreement.getMainTenant(), agreement.getSubTenants(), agreement.getHosts(), agreement.getOwner(), agreement.getProperty().getAddress(), agreement.getPeriod(), agreement.getContractDate(), agreement.getRentingFee(), agreement.getStatus());
        }

        System.out.print("Do you want to save this report? (yes/no): ");
        String saveReport = scanner.nextLine();
        if (saveReport.equalsIgnoreCase("yes")) {
            System.out.print("Enter the name for the report file: ");
            String filename = scanner.nextLine();
            try {
                ReportGenerator.generateReport(agreements, filename);
                System.out.println("Report saved successfully.");
            } catch (IOException e) {
                System.out.println("Error saving report: " + e.getMessage());
            }
        }
    }

    private void getRentalAgreementsByStatus(Scanner scanner) {
        System.out.print("Enter Status (NEW, ACTIVE, COMPLETED): ");
        String status = scanner.nextLine();

        System.out.println("1. Sort by ID (Ascending)");
        System.out.println("2. Sort by Contract Date (Earliest to Latest)");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        List<RentalAgreement> agreements = rentalManager.getRentalAgreementsByStatus(status);
        switch (choice) {
            case 1:
                Sorter.sortById(agreements);
                break;
            case 2:
                Sorter.sortByContractDate(agreements);
                break;
            default:
                System.out.println("Invalid choice. Returning to menu.");
                return;
        }

        System.out.println("ID    Main Tenant         Sub Tenants       Host              Owner             Property Address  Period   Contract Date  Renting Fee  Status");
        for (RentalAgreement agreement : agreements) {
            System.out.printf("%-5s %-20s %-20s %-20s %-20s %-20s %-10s %-15s %-10.2f %-10s%n", agreement.getId(), agreement.getMainTenant(), agreement.getSubTenants(), agreement.getHosts(), agreement.getOwner(), agreement.getProperty().getAddress(), agreement.getPeriod(), agreement.getContractDate(), agreement.getRentingFee(), agreement.getStatus());
        }

        System.out.print("Do you want to save this report? (yes/no): ");
        String saveReport = scanner.nextLine();
        if (saveReport.equalsIgnoreCase("yes")) {
            System.out.print("Enter the name for the report file: ");
            String filename = scanner.nextLine();
            try {
                ReportGenerator.generateReport(agreements, filename);
                System.out.println("Report saved successfully.");
            } catch (IOException e) {
                System.out.println("Error saving report: " + e.getMessage());
            }
        }
    }
}