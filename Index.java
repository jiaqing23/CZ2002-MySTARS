import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Index implements Serializable{

    private Course course;
    private String indexNo;
    private int classSize;
    private int vacancy;
    private ArrayList<Student> registeredStud = new ArrayList<Student>();
    private Queue<Student> waitlist = new LinkedList<Student>();
    private ArrayList<Class> classes = new ArrayList<Class>();

	//CONSTRUCTOR
    public Index(Course course, String indexNo, int classSize) {
        this.course = course;
        this.indexNo = indexNo;
        this.classSize = classSize;
        this.vacancy = classSize;
    }
	
	//Set and Get
    public void setIndexNo(String index) {
        this.indexNo = index;
    }
    
    public String getIndexNo(){
        return indexNo;
    }

    public void setClassSize(int classSize) {
        this.classSize = classSize;
    }

    public int getClassSize() {
        return classSize;
    }

    public void setVacancy(int vacancy) {
        this.vacancy = vacancy;
    }

    public int getVacancy() {
        return vacancy;
    }

    public ArrayList<Class> getClasses(){
        return classes;
    }

    public ArrayList<Student> getReg() {
        return registeredStud;
    }

	//CLASS METHOD
	//Add a new student into the index
    public void addReg(Student student) {
        registeredStud.add(student);
        vacancy--;
    }

	//Remove an existing student from the index
    public Student removeReg(Student student) {
        registeredStud.remove(student);
        vacancy++;
        if(!waitlist.isEmpty()){
            return waitlist.remove();
        }
        return null;
    }

	//Add a student into the waitlist
    public void addWaitlist(Student student) {
        waitlist.add(student);
    }

	//Remove a student from the waitlist
    public void removeWaitlist(Student student) {
        waitlist.remove(student);
    }

	
	//List all the particulars for index
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
