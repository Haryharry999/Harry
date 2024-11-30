package feature;

import rentalagreement.RentalAgreement;
import person.Tenant;
import person.Host;
import property.Property;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ReportGenerator {
    public static void generateReport(List<RentalAgreement> agreements, String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("ID,Main Tenant,Sub Tenants,Hosts,Owner,Property Address,Period,Contract Date,Renting Fee,Status\n");

            for (RentalAgreement agreement : agreements) {
                writer.write(agreement.getId() + "," +
                        agreement.getMainTenant().getFullName() + "," +
                        formatSubTenants(agreement) + "," +
                        formatHosts(agreement) + "," +
                        agreement.getOwner().getFullName() + "," +
                        agreement.getProperty().getAddress() + "," +
                        agreement.getPeriod() + "," +
                        agreement.getContractDate() + "," +
                        agreement.getRentingFee() + "," +
                        agreement.getStatus() + "\n");
            }
        }
    }

    private static String formatSubTenants(RentalAgreement agreement) {
        List<Tenant> subTenants = agreement.getSubTenants();
        return subTenants.stream()
                .map(Tenant::getFullName)
                .collect(Collectors.joining(", "));
    }

    private static String formatHosts(RentalAgreement agreement) {
        List<Host> hosts = agreement.getHosts();
        return hosts.stream()
                .map(Host::getFullName)
                .collect(Collectors.joining(", "));
    }
}