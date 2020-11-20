import java.io.Serializable;
import java.util.Date;

/**
 * Represents an Class of an Index of a Course.
 * Every Class should have a unique classID.
 */
public class Class implements Serializable, TimePeriod {

    /**
     * The class ID of this Class.
     */
    private String classID;

    /**
     * The type of this Class.
     * E.g. LEC, TUT, LAB, SEM, etc. 
     */
    private String type;

    /**
     * The start time of this Class.
     */
    private Date startTime;

    /**
     * The end time of this Class.
     */
    private Date endTime;

    /**
     * The venue of this Class.
     * E.g. LT2A, LHN TR+18, etc.
     */
    private String venue;

    /**
     * The group number of this Class.
     * E.g. SSR1, SS4, etc.
     */
    private String groupNo;

    /**
     * The week of occurence of this Class.
     * E.g. ODD, EVEN, BOTH.
     */
    private String week;
    
    /**
     * The day of week of this Class.
     * E.g. Monday, Tuesday, etc.
     */
    private String dayOfWeek;

    /**
     * Creates a new Class with the given parameters.
     * @param classID This Class's class ID.
     * @param type This Class's class type.
     * @param startTime This Class's start time.
     * @param endTime This Class's end time.
     * @param venue This Class's venue.
     * @param groupNo This Class's group number.
     * @param week This Class's week of occurence.
     * @param dayOfWeek This Class's day of week.
     */
    public Class(String classID, String type, Date startTime, Date endTime, String venue, String groupNo, String week, String dayOfWeek) {
        this.classID = classID;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
        this.groupNo = groupNo;
        this.week = week; //"ODD, EVEN, BOTH"
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * Method that changes the class ID of a Class.
     * @return The class ID of the calling Class object.
     */
    public String getClassID() {
        return classID;
    }

    /**
     * Method that changes the class type of a Class.
     * @return The class type of the calling Class object.
     */
    public String getType(){
        return type;
    }

    /**
     * Method that changes the start time of a Class.
     * @return The starting time of the calling Class object.
     */
    public Date getStartTimePeriod(){
        return startTime;
    }

    /**
     * Method that changes the end time of a Class.
     * @return The ending time of the calling Class object.
     */
    public Date getEndTimePeriod(){
        return endTime;
    }

    /**
     * Method that changes the venue of a Class.
     * @return The venue of the calling Class object.
     */
    public String getVenue(){
        return venue;
    }

    /**
     * Method that changes the group number of a Class.
     * @return The group number of the calling Class object.
     */
    public String getGroup(){
        return groupNo;
    }
    
    /**
     * Method that changes the week of occurence of a Class.
     * @return The week of occurence of the calling Class object.
     */
    public String getWeek(){
        return week;
    }

    /**
     * Method that changes the day of week of a Class.
     * @return The day of week of the calling Class object.
     */
    public String getDayOfWeek(){
        return dayOfWeek;
    }

    /**
     * Method that formats Date class object to print out as a timetable.
     * @return Return formatted results.
     */
    public String printTimePeriod(){
        String tem1 = Integer.toString(((int)startTime)) + Integer.toString((int)(60*(startTime-(int)startTime)));
        String tem2 = Integer.toString(((int)endTime)) + Integer.toString((int)(60*(endTime-(int)endTime)));
        return (tem1+"-"+tem2+" "+dayOfWeek+"("+week+")");
    }

    /**
     * Method that checks whether a Class is clashed with another Class.
     * @param anotherClass The target class to check with the calling Class object.
     * @return Returns true if there's a clash. Otherwise, returns false.
     */
    public boolean clash(Class anotherClass){
        if(!dayOfWeek.equals(anotherClass.getDayOfWeek()) || 
            (week.equals("ODD") && anotherClass.getWeek().equals("EVEN")) || (week.equals("EVEN") && anotherClass.getWeek().equals("ODD")) ||
            startTime.compareTo(anotherClass.getEndTimePeriod()) > 0 || anotherClass.getStartTimePeriod().compareTo(endTime) > 0)
            return false;
        return true;
    }

}
