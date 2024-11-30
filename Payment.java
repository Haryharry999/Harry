package payment;

/**
 * @author Your Name
 */
public class Payment {
    private int id;
    private String tenantName;
    private double amount;
    private String date;
    private String method;

    public Payment(int id, String tenantName, double amount, String date, String method) {
        this.id = id;
        this.tenantName = tenantName;
        this.amount = amount;
        this.date = date;
        this.method = method;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTenantName() { return tenantName; }
    public void setTenantName(String tenantName) { this.tenantName = tenantName; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", tenantName='" + tenantName + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}