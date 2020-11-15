import java.util.Date;

public class Class {

    private String type;
    private Date startTime;
    private Date endTime;
    private String venue;
    private String groupNo;
    private String week;

    public Class(String type, Date startTime, Date endTime, String venue, String groupNo, String week) {
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
        this.groupNo = groupNo;
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

    public String getGroup(){
        return groupNo;
    }
    
    public String getWeek(){
        return week;
    }

    public boolean clash(Class anotherClass){
        if(startTime.compareTo(anotherClass.getEndTime()) > 0 || 
            anotherClass.getStartTime().compareTo(endTime) > 0)
            return true;
        return false;
    }
}
