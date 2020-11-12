import java.text.DateFormat;
import java.util.*;

public class Period {
    Calendar c = Calendar.getInstance();
    
    // startPeriod: 25nd December 2020, 12PM 
    private Date startPeriod;
    // startPeriod: 25nd December 2020, 3PM 
    private Date endPeriod;

    public Period(){
        c.set(2020, 9, 25, 12, 00, 00);
        this.startPeriod = c.getTime();
        c.set(2020, 10, 25, 15, 00, 00);
        this.endPeriod = c.getTime();

        System.out.println(startPeriod);
        System.out.println(endPeriod);
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


    // DEBUG //
    public static void main(String args[]){

        Period period = new Period();

        Calendar current = Calendar.getInstance();
        Date currentPeriod = current.getTime();

        System.out.println(current);
        System.out.println(currentPeriod);

        boolean inPeriod = period.validatePeriod(currentPeriod);

        if(!inPeriod)
            System.out.println("It's still not the registration period!");
        else
            System.out.println("You're in!");


    }
    
}
