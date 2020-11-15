import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable{
	private String school;
	private String courseCode;
	private String courseName;
	private int numOfAU;
	private ArrayList<Index> indexes;
	
	
	public void addIndex(Index index) {
		indexes.add(index);
	}
	
	public void dropIndex(Index index) {
		indexes.remove(index);
	}
	
	public void listIndex() {
		System.out.println("Indexes available: ");
		for(int i=0; i<indexes.size();i++) {
			System.out.println(indexes.get(i));
		}
	}
	
	
	//Get and Set
	public void setNumOfAU(int numOfAU) {
		this.numOfAU = numOfAU;
	}
	
	public int getNumOfAU() {
		return numOfAU;
	}
	
	public void setCourseName(String courseName) {
		this.courseName=courseName;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
	public String getCourseCode() {
		return courseCode;
	}
	
	public void setSchool(String school) {
		this.school = school;
	}
	
	public String getSchool() {
		return school;
	}

}
