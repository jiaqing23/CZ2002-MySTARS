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
	public void addIndex(Index index) {
		indexes.add(index);
	}
	
	public void dropIndex(Index index) {
		indexes.remove(index);
	}

	public void updateIndex(Index index) {

		System.out.println("(1) Update IndexNo");
		System.out.println("(2) Update vacancy");
		System.out.println("(3) Add class");
		System.out.println("(4) Remove class");
		System.out.println("Select Option: ");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
		sc.next();
		
		String tem;
		do{
			switch(option){
				case 1:
					System.out.print("New IndexNo: ");
					tem = sc.nextLine();
					index.setIndexNo(tem);
					break;
		
				case 2:
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
					break;

				case 3:
					System.out.println("Class ID: ");
					String classID = sc.nextLine();
					System.out.println("Type of class: ");
					String type = sc.nextLine();
					System.out.println("Start time: ");
					int startTime = sc.nextInt(); sc.nextLine();
					System.out.println("End time: ");
					int endTime = sc.nextInt(); sc.nextLine();
					System.out.println("Venue: ");
					String venue = sc.nextLine();
					System.out.println("Group number: ");
					String groupNo = sc.nextLine();
					System.out.println("Week(ODD, EVEN, BOTH): ");
					String week = sc.nextLine();
					System.out.println("Day of week: ");
					String dayOfWeek = sc.nextLine();
	
					Class temClass = new Class(classID, type, startTime, endTime, venue, groupNo, week, dayOfWeek);
					index.addClass(temClass);
					break;

				case 4:
					System.out.println("Class ID to be dropped: ");
					tem = sc.nextLine();
	
					for(Class c : index.getClasses()){
						if(c.getClassID() == tem){
							index.removeClass(c);
							break;
						}
					}
					break;
					
				default:
					System.out.println("Invalid option!");
					break;
			}
		} while(option < 1 || option > 4);
	}
	
	public void listIndex() {
		System.out.println("Indexes available: ");
		for(int i=0; i<indexes.size();i++) {
			System.out.println(indexes.get(i));
		}
	}

}
