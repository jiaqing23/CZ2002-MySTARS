import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a Course created by an Admin.
 * Every course should have a distinct course code.
 */
public class Course implements Serializable{
	/**
	 * The school name that provides this Course.
	 */
	private String school;
	/**
	 * The course code of this Course.
	 */
	private String courseCode;
	/**
	 * The name of this Course.
	 */
	private String courseName;
	/**
	 * The number of academic units of this Course.
	 */
	private int numOfAU;
	/**
	 * All the indexes of this Course.
	 */
	private ArrayList<Index> indexes = new ArrayList<Index>();
	
	/**
	 * Creates a new Course with the given parameters.
	 * @param school This Course's school name.
	 * @param courseCode This Course's course code.
	 * @param courseName This Course's name.
	 * @param numOfAU This Course's number of academic units.
	 */
	public Course(String school, String courseCode, String courseName, int numOfAU) {
		this.school = school;
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.numOfAU = numOfAU;
	}

	/**
	 * Method that changes the school name of a Course.
	 * @param school The new school name of the calling Course object.
	 */
	public void setSchool(String school) {
		this.school = school;
	}

	/**
	 * Method that gets the school name of a Course.
	 * @return The school name of a calling Course object.
	 */
	public String getSchool() {
		return school;
	}

	/**
	 * Method that changes the course code of a Course.
	 * @param courseCode The new course code of the calling Course object.
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
	/** Method that gets the course code of a Course.
	 * @return The course code of the calling Course object.
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 * Method that changes the course name of a Course.
	 * @param courseName The new course name of the calling Course object.
	 */
	public void setCourseName(String courseName) {
		this.courseName=courseName;
	}
	
	/**
	 * Method that gets the course name of a Course.
	 * @return The course name of the calling Course object.
	 */
	public String getCourseName() {
		return courseName;
	}
	
	/**
	 * Method that changes the number of academic units of a Course.
	 * @param numOfAU The new number of academic units of the calling Course object.
	 */
	public void setNumOfAU(int numOfAU) {
		this.numOfAU = numOfAU;
	}

	/**
	 * Method that gets the number of academic units of a Course.
	 * @return The number of academic units of the calling Course object.
	 */
	public int getNumOfAU() {
		return numOfAU;
	}
	
	/**
	 * Method that gets all the Index objects of a Course.
	 * @return All the Index objects of the calling Course object.
	 */
	public ArrayList<Index> getIndexes() {
		return indexes;
	}

	/**
	 * Method that adds a new Index to an existing Course.
	 * @param index The new Index object to be added to the calling Course object.
	 */
	public void addIndex(Index index) {
		indexes.add(index);
	}
	
	/**
	 * Method that drops an existing Index from an existing Course.
	 * @param index The existing Index object to be dropped from the calling Course object.
	 */
	public void dropIndex(Index index) {
		indexes.remove(index);
	}

	/**
	 * Method that updates an existing Index of an existing Course.
	 * @param index The existing Index object to be updated of an existing Course.
	 */
	public void updateIndex(Index index) {
		System.out.println("(1) Update IndexNo");
		System.out.println("(2) Update vacancy");
		System.out.println("(3) Add class");
		System.out.println("(4) Remove class");
		System.out.println("Select Option: ");
        Scanner sc = new Scanner(System.in);
        int option = MySTARS.readInt();
		
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
					if(index.getVacancy() != index.getClassSize()){
						System.out.println("This index already registered by someone, cannot add class.");
						break;
					}
					System.out.println("Class ID: ");
					String classID = sc.nextLine();
					System.out.println("Type of class: ");
					String type = sc.nextLine();
					System.out.println("Start time: ");
					int startTime = MySTARS.readInt();
					System.out.println("End time: ");
					int endTime = MySTARS.readInt();
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
					if(index.getVacancy() != index.getClassSize()){
						System.out.println("This index already registered by someone, cannot remove class.");
						break;
					}
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
	
	/**
	 * Method that prints out all the indexes of a Course.
	 */
	public void listIndex() {
		System.out.println("Indexes available: ");
		for(int i=0; i<indexes.size();i++) {
			System.out.println(indexes.get(i));
		}
	}

}
