import java.util.*;

public class Class {
    private String type;
    private Calendar startTime;
    private Calendar endTime;
    private String venue;
    private String week;

    public Class(String type, Calendar startTime, Calendar endTime, String venue, String week) {
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
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

    public String week(){
        return week;
    }
}
