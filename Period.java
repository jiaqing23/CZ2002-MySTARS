import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Represents the course registration Period of the University.
 * Admin can access the system regardless of the registration period.
 * Student can only access the system within the registration period.
 */
public class Period implements Serializable{
    Calendar calendar = Calendar.getInstance();
    private Date startPeriod;
    private Date endPeriod;

    /**
     * Creates a default registration Period.
     * <p>
     * Default start period: 25th October 2020, 12PM
     * <p>
     * Default end period: 25th November 2020, 12PM
     */
    public Period(){

        // print format: Wed Nov 25 15:00:00 SGT 2020
        calendar.set(2020, 9, 25, 12, 00, 00);
        this.startPeriod = calendar.getTime();

        calendar.set(2020, 10, 25, 15, 00, 00);
        this.endPeriod = calendar.getTime();
    }

    /**
     * Method that gets the start time of the registration Period.
     * @return Start time Date object of the calling Period object.
     */
    public Date getStartPeriod() {
        return startPeriod;
    }

    /**
     * Method that gets the end time of the registration Period.
     * @return End time Date object of the calling Period object.
     */
    public Date getEndPeriod() {
        return endPeriod;
    }

    /**
     * Method that changes the start time and end time of the registration Period.
     * @param startPeriod The new start time Date object of the calling Period object.
     * @param endPeriod The new end time Date object of the calling Period object.
     */
    public void setPeriod(Date startPeriod, Date endPeriod) {
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
    }

    /**
     * Method that validates the current time with the registration Period.
     * @return True if current time is within the registration Period, else return false.
     */
    public boolean validatePeriod() {
        Date currentDatetime = new Date();
        if(currentDatetime.compareTo(startPeriod) < 0 || currentDatetime.compareTo(endPeriod) > 0)
            return false;
        
        return true;
    }

    /**
     * Method that prints the registration Period.
     */
    public void printPeriod(){
        System.out.println("Start period: " + startPeriod);
        System.out.println("End period: " + endPeriod);
    }

}
