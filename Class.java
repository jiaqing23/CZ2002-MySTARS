import java.io.Serializable;
import java.util.Date;
import java.time.*;

public class Class implements Serializable{

    private String classID;
    private String type;
    private int startTime;
    private int endTime;
    private String venue;
    private String groupNo;
    private String week;
    private String dayOfWeek;

    // CONSTRUCTOR
    public Class(String classID, String type, int startTime, int endTime, String venue, String groupNo, String week, String dayOfWeek) {
        this.classID = classID;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
        this.groupNo = groupNo;
        this.week = week; //"ODD, EVEN, BOTH"
        this.dayOfWeek = dayOfWeek;
    }

    public String getClassID() {
        return classID;
    }

    public String getType(){
        return type;
    }

    public int getStartTime(){
        return startTime;
    }

    public int getEndTime(){
        return endTime;
    }

    public String getVenue(){
        return venue;
    }

    public String getGroup(){
        return groupNo;
    }
    
    public String getWeek(){
        return week;
    }

    public String getDayOfWeek(){
        return dayOfWeek;
    }

    // CLASS METHODS
    public boolean clash(Class anotherClass){

        if(dayOfWeek.equals(anotherClass.getDayOfWeek()) || 
            (week.equals("ODD") && anotherClass.getWeek().equals("EVEN")) || (week.equals("EVEN") && anotherClass.getWeek().equals("ODD")) ||
            startTime > anotherClass.getEndTime() || anotherClass.getStartTime() > endTime)
            return true;
        return false;
    }

}
