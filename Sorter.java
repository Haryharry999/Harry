package feature;

import rentalagreement.RentalAgreement;

import java.util.Comparator;
import java.util.List;

public class Sorter {
    public static void sortById(List<RentalAgreement> agreements) {
        agreements.sort(Comparator.comparing(RentalAgreement::getId));
    }

    public static void sortByContractDate(List<RentalAgreement> agreements) {
        agreements.sort(Comparator.comparing(RentalAgreement::getContractDate));
    }

    public static void sortByStatus(List<RentalAgreement> agreements) {
        agreements.sort(Comparator.comparing(RentalAgreement::getStatus));
    }
}