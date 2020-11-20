import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Represents the course registration Period of the University.
 * Admin can access the system regardless of the registration period.
 * Student can only access the system within the registration period.
 */
public class Period implements Serializable{
    /**
     * Period class object is associated with the Calendar class instance.
     */
    Calendar calendar = Calendar.getInstance();

    /**
     * The start time of the registration Period.
     */
    private Date startPeriod;
    
    /**
     * The end time of the registration Period.
     */
    private Date endPeriod;

    /**
     * Creates a default registration Period.
     * <p>
     * Default start period: 1st December 2020, 12AM
     * <p>
     * Default end period: 31st December 2020, 12AM
     */
    public Period(){

        // print format: Wed Oct 25 12:00:00 SGT 2020
        calendar.set(2020, 11, 1, 00, 00, 00);
        this.startPeriod = calendar.getTime();

        calendar.set(2020, 11, 31, 00, 00, 00);
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

        if(startPeriod.compareTo(endPeriod) > 0)
            System.out.println("Start period can't be smaller than end period!");
        else{
            this.startPeriod = startPeriod;
            this.endPeriod = endPeriod;
        }
        
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
