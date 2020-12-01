import java.util.Date;

public interface TimePeriod {

    /**
     * Abstract method that gets time/period object and convert it to String object.
     * @return Formatted String object
     */
    public abstract String getTimePeriodString();

    /**
     * Abstract method that gets start time/period.
     * @return Start time Date object.
     */ 
    public abstract Date getStartTimePeriod();

    /**
     * Abstract method that gets end time/period.
     * @return End time Date object.
     */ 
    public abstract Date getEndTimePeriod();

}
