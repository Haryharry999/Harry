package feature;

import rentalagreement.RentalAgreement;

import java.util.List;

public interface RentalManager {
    void addRentalAgreement(RentalAgreement agreement);
    void updateRentalAgreement(int id, RentalAgreement updatedAgreement);
    void deleteRentalAgreement(int id);
    List<RentalAgreement> getAllRentalAgreements();
    List<RentalAgreement> getRentalAgreementsByOwnerName(String ownerName);
    List<RentalAgreement> getRentalAgreementsByPropertyAddress(String address);
    List<RentalAgreement> getRentalAgreementsByStatus(String status);
}