package property;

/**
 * Class representing the status of a property.
 * Utilized across different property types.
 *
 * Author: Your Name
 */
public class PropertyStatus{
    private String status;

    // Constructor to initialize the status
    public PropertyStatus(String status) {
        this.status = status;
    }

    // Getter for status
    public String getStatus() {
        return status;
    }

    // Setter for status
    public void setStatus(String status) {
        this.status = status;
    }

    // String representation of the PropertyStatus
    @Override
    public String toString() {
        return "PropertyStatus{" +
                "status='" + status + '\'' +
                '}';
    }
}