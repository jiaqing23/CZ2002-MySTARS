import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable{
	private String school;
	private String courseCode;
	private String courseName;
	private int numOfAU;
	private ArrayList<Index> indexes = new ArrayList<Index>();
	
	// CONSTRUCTORS
	public Course(String school, String courseCode, String courseName, int numOfAU) {
		this.school = school;
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.numOfAU = numOfAU;
	}

	// SET AND GET METHODS
	public void setSchool(String school) {
		this.school = school;
	}

	public String getSchool() {
		return school;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseName(String courseName) {
		this.courseName=courseName;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setNumOfAU(int numOfAU) {
		this.numOfAU = numOfAU;
	}
	
	public int getNumOfAU() {
		return numOfAU;
	}
	
	public ArrayList<Index> getIndexes() {
		return indexes;
	}

	// CLASS METHODS
	public void addIndex(Index index) {
		indexes.add(index);
	}
	
	public void dropIndex(Index index) {
		indexes.remove(index);
	}

	public void updateIndex(Index index) {
		
	}
	
	public void listIndex() {
		System.out.println("Indexes available: ");
		for(int i=0; i<indexes.size();i++) {
			System.out.println(indexes.get(i));
		}
	}
	
}
