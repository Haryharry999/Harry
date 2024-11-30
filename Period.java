package rentalagreement;

/**
 * @author Your Name
 */
public class Period{
    private String period;

    public Period(String period) {
        this.period = period;
    }

    // Getters and Setters
    public String getPeriod() { return period; }
    public void setPeriod(String period) { this.period = period; }

    @Override
    public String toString() {
        return "Period{" +
                "period='" + period + '\'' +
                '}';
    }
}