import java.util.Date;

public class Class {

    private String type;
    private Date startTime;
    private Date endTime;
    private String venue;
    private String week;

    public Class(String type, Date startTime, Date endTime, String venue, String week) {
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
        this.week = week;
    }

    public String getType(){
        return type;
    }

    public Date getStartTime(){
        return startTime;
    }

    public Date getEndTime(){
        return endTime;
    }

    public String getVenue(){
        return venue;
    }

    public String week(){
        return week;
    }

    public boolean clash(Class anotherClass){
        if(startTime.compareTo(anotherClass.getEndTime()) > 0 || 
            anotherClass.getStartTime().compareTo(endTime) > 0)
            return true;
        return false;
    }
}
