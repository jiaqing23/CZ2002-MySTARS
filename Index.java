import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
public class Index {
	
	private String courseCode;
	private String indexNo;
	private int classSize;
	private int vacancy;
	private ArrayList<Student> registeredStud;
	private Queue<Student> waitlist;
	private ArrayList<Class> classes;

	public boolean addReg(Student student) {//add a student to this index 
		registeredStud.add(student));//?why need to return boolean value?
		vacancy--;
		return true;//??why need to return boolean value?
	}
	public boolean dropReg(Student student) {//drop a student from this index
		registeredStud.remove(student);
		vacancy++;
		return true;
	}
	public void addWaitlist(Student student) {
		waitlist.add(student);
	}
	public Student dropWaitlist(Student student) {
		return(waitlist.poll());
	}
	public void listClass() {
		Iterator<Class> iter= classes.iterator(); 
		if (!iter.hasNext())
			 System.out.println("This index does not have any classes yet.");
		else
			System.out.println("The classes of this index are: ");
		while (iter.hasNext()) { 
            System.out.println(iter.next()); 
        } 
	}
	
	public String getCourseCode() {
		return courseCode;
	}
	public String indexNo() {
		return indexNo;
	}
	public int getClassSize() {
		return classSize;
	}
	public int getVacancy() {
		return vacancy;
	}
	public ArrayList<Class> getClasses(){
		return classes;
	}
	public ArrayList<Student> getRegisteredStud(){
		return registeredStud;
	}
	public Queue<Student> getWaitlist(){
		return waitlist;
	}
	public void setClassSize(int classSize) {
		this.classSize=classSize;
	}
}
