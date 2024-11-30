package rentalagreement;

/**
 * @author Your Name
 */
public class AgreementStatus {
    private String status;

    public AgreementStatus(String status) {
        this.status = status;
    }

    // Getters and Setters
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "AgreementStatus{" +
                "status='" + status + '\'' +
                '}';
    }
}