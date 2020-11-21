import java.util.Date;

public interface TimePeriod {

    // public abstract boolean validateTimePeriod();
    public abstract String getTimePeriodString();
    public abstract Date getStartTimePeriod();
    public abstract Date getEndTimePeriod();

}
