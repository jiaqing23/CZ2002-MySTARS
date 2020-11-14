import java.util.*;

public class Period {
    Calendar calendar = Calendar.getInstance();
    
    private Date startPeriod;
    private Date endPeriod;

    public Period(){
        // Default startPeriod: 25th October 2020, 12PM
        c.set(2020, 9, 25, 12, 00, 00);
        this.startPeriod = calendar.getTime();

        // Default startPeriod: 25th November 2020, 12PM
        c.set(2020, 10, 25, 15, 00, 00);
        this.endPeriod = calendar.getTime();
    }

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

    public void setPeriod(Date startPeriod, Date endPeriod) {
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
    }
}
