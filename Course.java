import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

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
	//Used to add a new index
	public void addIndex(Index index) {
		indexes.add(index);
	}
	
	//Used to drop an existing index 
	public void dropIndex(Index index) {
		indexes.remove(index);
	}

	//Used to update the index number or vacancy of an index
	public void updateIndex(Index index) {

		System.out.println("(1) Update IndexNo");
		System.out.println("(2) Update vacancy");
		System.out.println("Select Option: ");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
		sc.next();
		
		String tem;

		do{
			if(option == 1){
				System.out.print("New IndexNo: ");
				tem = sc.nextLine();
				index.setIndexNo(tem);
			}
			else if(option == 2){
				System.out.print("New class size: ");
				tem = sc.nextLine();
				int newSize = Integer.parseInt(tem);

				// We can't update the newSize lesser than the original number of vacancy
				if((index.getClassSize() - newSize) > index.getVacancy()){
					System.out.println("New class size is too small for registered students!");
				}
				else{
					index.setClassSize(newSize);
				}
			}

			System.out.println("Invalid option!");
		} while(option!= 1 && option != 2);
	}
	
	//Used to list out the available indexes for a course
	public void listIndex() {
		System.out.println("Indexes available: ");
		for(int i=0; i<indexes.size();i++) {
			System.out.println(indexes.get(i));
		}
	}

	
}
