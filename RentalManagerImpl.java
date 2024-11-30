package feature;

import rentalagreement.RentalAgreement;

import java.util.ArrayList;
import java.util.List;

public class RentalManagerImpl implements RentalManager {
    private final List<RentalAgreement> rentalAgreements = new ArrayList<>();

    @Override
    public void addRentalAgreement(RentalAgreement agreement) {
        rentalAgreements.add(agreement);
    }

    @Override
    public void updateRentalAgreement(int id, RentalAgreement updatedAgreement) {
        for (int i = 0; i < rentalAgreements.size(); i++) {
            RentalAgreement currentAgreement = rentalAgreements.get(i);
            if (currentAgreement.getId() == id) {  // Corrected condition for integer comparison
                rentalAgreements.set(i, updatedAgreement);
                return;
            }
        }
    }

    @Override
    public void deleteRentalAgreement(int id) {
        rentalAgreements.removeIf(agreement -> agreement.getId() == id);  // Corrected lambda expression for integer comparison
    }

    @Override
    public List<RentalAgreement> getAllRentalAgreements() {
        return new ArrayList<>(rentalAgreements);
    }

    @Override
    public List<RentalAgreement> getRentalAgreementsByOwnerName(String ownerName) {
        List<RentalAgreement> result = new ArrayList<>();
        for (RentalAgreement agreement : rentalAgreements) {
            if (agreement.getOwner().getFullName().equalsIgnoreCase(ownerName)) {
                result.add(agreement);
            }
        }
        return result;
    }

    @Override
    public List<RentalAgreement> getRentalAgreementsByPropertyAddress(String address) {
        List<RentalAgreement> result = new ArrayList<>();
        for (RentalAgreement agreement : rentalAgreements) {
            if (agreement.getProperty().getAddress().equalsIgnoreCase(address)) {
                result.add(agreement);
            }
        }
        return result;
    }

    @Override
    public List<RentalAgreement> getRentalAgreementsByStatus(String status) {
        List<RentalAgreement> result = new ArrayList<>();
        for (RentalAgreement agreement : rentalAgreements) {
            if (agreement.getStatus().equalsIgnoreCase(status)) {
                result.add(agreement);
            }
        }
        return result;
    }
}