import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;


public class Period implements Serializable{
    Calendar calendar = Calendar.getInstance();
    private Date startPeriod;
    private Date endPeriod;

    // CONSTRUCTOR
    public Period(){

        // Default startPeriod: 25th October 2020, 12PM
        // print format: Wed Nov 25 15:00:00 SGT 2020
        calendar.set(2020, 9, 25, 12, 00, 00);
        this.startPeriod = calendar.getTime();

        // Default startPeriod: 25th November 2020, 12PM
        calendar.set(2020, 10, 25, 15, 00, 00);
        this.endPeriod = calendar.getTime();
    }

    // SET AND GET METHODS
    public Date getStartPeriod() {
        return startPeriod;
    }

    public Date getEndPeriod() {
        return endPeriod;
    }

    public void setPeriod(Date startPeriod, Date endPeriod) {
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
    }

    // CLASS METHODS
    public boolean validatePeriod() {
        Date currentDatetime = new Date();
        if(currentDatetime.compareTo(startPeriod) < 0 || currentDatetime.compareTo(endPeriod) > 0)
            return false;
        
        return true;
    }

    public void printPeriod(){
        System.out.println("Start period: " + startPeriod);
        System.out.println("End period: " + endPeriod);
    }

}
