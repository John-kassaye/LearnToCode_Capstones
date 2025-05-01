import java.time.LocalDate;
import java.time.LocalTime;

public class TransactionRecord {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private float amount;


    public TransactionRecord(LocalDate date, LocalTime time, String description, String vendor, float amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public float getAmount() {
        return amount;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String toString(){
        return String.format("%s  |  %s  |  %s  |  %s  |%.2f ", date, time, description, vendor, amount);
    }
}
