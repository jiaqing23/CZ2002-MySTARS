import java.util.*;

public class Period {
    Calendar c = Calendar.getInstance();
    
    private Date startPeriod;
    private Date endPeriod;

    public Period(){

        // Default startPeriod: 25th October 2020, 12PM
        c.set(2020, 9, 25, 12, 00, 00);
        this.startPeriod = c.getTime();

        // Default startPeriod: 25th November 2020, 12PM
        c.set(2020, 10, 25, 15, 00, 00);
        this.endPeriod = c.getTime();
    }

    public boolean validatePeriod(Date currentPeriod) {
        if(currentPeriod.compareTo(startPeriod) == -1 || currentPeriod.compareTo(endPeriod) == 1)
            return false;

        return true;
    }

    public void setPeriod(Date startPeriod, Date endPeriod) {
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
    }
}
