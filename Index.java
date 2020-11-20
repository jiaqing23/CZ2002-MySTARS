import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents an Index of a Course.
 * Every index should have a unique index number. 
 */
public class Index implements Serializable{

    /**
     * The Course that this Index belongs to.
     */
    private Course course;

    /**
     * The index number of this Index.
     */
    private String indexNo;

    /**
     * The class size of this Index.
     */
    private int classSize;

    /**
     * The current vacancy of this Index.
     */
    private int vacancy;

    /**
     * The list of registered students of this Index.
     */
    private ArrayList<Student> registeredStud = new ArrayList<Student>();

    /**
     * The queue of students that have registered for this Index.
     */
    private Queue<Student> waitlist = new LinkedList<Student>();

    /**
     * The list of classes of this Index.
     */
    private ArrayList<Class> classes = new ArrayList<Class>();

    
    /**
     * Creates a new Index with the given parameters.
     * @param course This course name that this Index belongs to.
     * @param indexNo This Index's index number.
     * @param classSize This Index's class size.
     */
    public Index(Course course, String indexNo, int classSize) {
        this.course = course;
        this.indexNo = indexNo;
        this.classSize = classSize;
        this.vacancy = classSize;
    }
	
	/**
     * Method that changes the index number of an Index.
     * @param index The new index number of the calling Index object.
     */
    public void setIndexNo(String index) {
        this.indexNo = index;
    }

    /**
     * Method that gets the Course object of an Index.
     * @return The Course object of the calling Index object.
     */
    public Course getCourse(){
        return course;
    }
    
    /**
     * Method that gets the index number of an Index.
     * @return The index number of the calling Index object.
     */
    public String getIndexNo(){
        return indexNo;
    }

    /**
     * Method that changes the class size of an Index.
     * @param classSize The new class size of the calling Index object.
     */
    public void setClassSize(int classSize) {
        this.classSize = classSize;
    }

    /**
     * Method that gets the class size of an Index.
     * @return The class size of the calling Index object.
     */
    public int getClassSize() {
        return classSize;
    }

    /**
     * Method that gets the current vacancy of an Index.
     * @return The current vacancy of the calling Index object.
     */
    public int getVacancy() {
        return vacancy;
    }

    /**
     * Method that adds a new class to an Index.
     * @param temClass The new class to be added to the calling Index object.
     */
    public void addClass(Class temClass) {
        for(Class c: classes){
            if(c.clash(temClass)){
                System.out.println("This class clash with another class of this index!");
                return;
            }
        }
        classes.add(temClass);
    }

    /**
     * Method that removes an existing class of an Index.
     * @param temClass The existing class to be removed from the calling Index object.
     */
    public void removeClass(Class temClass) {
        classes.remove(temClass);
    }

    /**
     * Method that gets all the classes of an Index.
     * @return The list of classes of the calling Index object.
     */
    public ArrayList<Class> getClasses(){
        return classes;
    }

    /**
     * Method that gets all the registered students of an Index.
     * @return The list of registered students of the calling Index object.
     */
    public ArrayList<Student> getReg() {
        return registeredStud;
    }

    /**
     * Method that registers a existing Student to an Index.
     * @param student The Student object to be registered to the calling Index object.
     */
    public void addReg(Student student) {
        registeredStud.add(student);
        Notification.sendMail(student.getEmail(), student.getName(), student.getMatriculationNumber(),
                            getCourse().getCourseName(), getCourse().getCourseCode(), getIndexNo());
        vacancy--;
    }

    /**
     * Method that removes a existing Student from an Index.
     * @param student The existing Student object to be removed from the calling Index object.
     */
    public void removeReg(Student student) {
        registeredStud.remove(student);
        vacancy++;
    }

	/**
     * Method that adds an existing Student to the waitlist of an Index.
     * @param student The existing Student object to be added to the waitlist of the calling Index object.
     */
    public void addWaitlist(Student student) {
        waitlist.add(student);
    }

	/**
     * Method that removes an existing Student from the waitlist of an Index.
     * @param student The existing Student object to be removed from the waitlist of the calling Index object.
     */
    public void removeWaitlist(Student student) {
        waitlist.remove(student);
    }

    /**
     * Method that dequeue the first Student object from the waitlist of an Index.
     * @return The first Student object in the waitlist of the calling Index object.
     */
    public Student popWaitlist() {
        if(!waitlist.isEmpty())
            return waitlist.remove();
        return null;
    }

    /**
     * Method that gets the number of students in the waitlist of an Index.
     * @return The number of students in the waitlist of the calling Index object.
     */
    public int getWaitlistLength(){
        return waitlist.size();
    }

    /**
     * Method that list all the classes of an Index.
     */
    public void listClass() {
		if (classes.isEmpty())
			 System.out.println("This index does not have any classes yet.");
		else{
            System.out.println("The classes of this index are: ");
            System.out.println("Type\tGroup\tVenue\tTime");
            for(Class c : classes){
                System.out.println(c.getType() + "\t" + c.getGroup() + "\t" + c.getVenue() + "\t" + c.getStartTime() + "-" + c.getEndTime() + " " +
                                    c.getWeek());
            }
        }
    }

}
