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

    public Index(Course course, String indexNo, int classSize) {
        this.course = course;
        this.indexNo = indexNo;
        this.classSize = classSize;
        this.vacancy = classSize;
    }


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

    public void addReg(Student student) {
        registeredStud.add(student);
        vacancy--;
    }

    public void removeReg(Student student) {
        registeredStud.remove(student);
        vacancy++;
    }

    public void addWaitlist(Student student) {
        waitlist.add(student);
    }

    public void removeWaitlist(Student student) {
        waitlist.remove(student);
    }

    public Student popWaitlist() {
        if(!waitlist.isEmpty())
            return waitlist.remove();
        return null;
    }

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
