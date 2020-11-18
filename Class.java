import java.io.Serializable;
import java.util.Date;
import java.time.*;

/**
 * Represents an Class of an Index of a Course.
 * Every Class should have a distinct classID.
 */
public class Class implements Serializable{

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
    private int startTime;
    /**
     * The end time of this Class.
     */
    private int endTime;
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
     * @return Nothing.
     */
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

    /**
     * Method to get the class ID of a Class.
     * @return The class ID of the calling Class object.
     */
    public String getClassID() {
        return classID;
    }

    /**
     * Method to get the class type of a Class.
     * @return The class type of the calling Class object.
     */
    public String getType(){
        return type;
    }

    /**
     * Method to get the start time of a Class.
     * @return The starting time of the calling Class object.
     */
    public int getStartTime(){
        return startTime;
    }

    /**
     * Method to get the end time of a Class.
     * @return The ending time of the calling Class object.
     */
    public int getEndTime(){
        return endTime;
    }

    /**
     * Method to get the venue of a Class.
     * @return The venue of the calling Class object.
     */
    public String getVenue(){
        return venue;
    }

    /**
     * Method to get the group number of a Class.
     * @return The group number of the calling Class object.
     */
    public String getGroup(){
        return groupNo;
    }
    
    /**
     * Method to get the week of occurence of a Class.
     * @return The week of occurence of the calling Class object.
     */
    public String getWeek(){
        return week;
    }

    /**
     * Method to get the day of week of a Class.
     * @return The day of week of the calling Class object.
     */
    public String getDayOfWeek(){
        return dayOfWeek;
    }

    /**
     * Method to check whether a Class is clashed with another Class.
     * @param anotherClass The target class to check with the calling Class object.
     * @return Returns true if there's a clash. Otherwise, returns false.
     */
    public boolean clash(Class anotherClass){

        if(dayOfWeek.equals(anotherClass.getDayOfWeek()) || 
            (week.equals("ODD") && anotherClass.getWeek().equals("EVEN")) || (week.equals("EVEN") && anotherClass.getWeek().equals("ODD")) ||
            startTime > anotherClass.getEndTime() || anotherClass.getStartTime() > endTime)
            return true;
        return false;
    }

}
