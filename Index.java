import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Index implements Serializable{

    private String courseCode;
    private String indexNo;
    private int classSize;
    private int vacancy;
    private ArrayList<Student> registeredStud = new ArrayList<Student>();
    private Queue<Student> waitlist = new LinkedList<Student>();
    private ArrayList<Class> classes = new ArrayList<Class>();

    public Index(String courseCode, String indexNo, int classSize, int vacancy) {
        this.courseCode = courseCode;
        this.indexNo = indexNo;
        this.classSize = classSize;
        this.vacancy = vacancy;
    }

    public void setClassSize(int classSize) {
        this.classSize = classSize;
    }

    public ArrayList<Class> getClasses(){
        return classes;
    }

    public int getVacancy() {
        return vacancy;
    }

    public String getIndexNo(){
        return indexNo;
    }

    public ArrayList<Student> getReg() {
        return registeredStud;
    }

    public void addReg(Student student) {
        registeredStud.add(student);
        vacancy--;
    }

    public Student removeReg(Student student) {
        registeredStud.remove(student);
        vacancy++;
        if(!waitlist.isEmpty()){
            return waitlist.remove();
        }
        return null;
    }

    public void addWaitlist(Student student) {
        waitlist.add(student);
    }

    public void removeWaitlist(Student student) {
        waitlist.remove(student);
    }

    public void listClass() {
        System.out.println("Course code: " + courseCode);
        System.out.println("Index no.: " + indexNo);

        for(Class c : classes){
            System.out.println("Class type: " + c.getType());
            System.out.println("Group no.: " + c.getGroup());
            System.out.println("Venue: " + c.getVenue());
            System.out.println("Time: " + c.getStartTime() + " - " + c.getEndTime());
            System.out.println("Week: " + c.getWeek());
        }
    }
     
}
