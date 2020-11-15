import java.util.*;

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

    public Calendar getStartTime(){
        return startTime;
    }

    public Calendar getEndTime(){
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

}
