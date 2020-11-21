import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

/**
 * Represents a Course created by an Admin.
 * Every course should have a unique course code.
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
	public void updateIndex(Index index, MySTARS mainApp) {
		int choice = 0;
        while(choice != 5){
			System.out.println();
			System.out.println("// --------------- Updating Index " + index.getIndexNo() + " --------------- //");
			System.out.println("(1) Update index number");
			System.out.println("(2) Update class size");
			System.out.println("(3) Add class");
			System.out.println("(4) Remove class");
			System.out.println("(5) Back to update course page");
			System.out.print("Select Option: ");
			Scanner sc = MySTARS.getScanner();
			choice = MySTARS.readInt();
			
			String tem;
			switch(choice){

				// UPDATE INDEX NUMBER //
				case 1:
					System.out.print("Please enter the new index number: ");
					tem = sc.nextLine();
					index.setIndexNo(tem);
					break;
				
				// UPDATE CLASS SIZE //
				case 2:
					System.out.print("Please enter the new class size: ");
					int newSize = MySTARS.readInt();
	
					// We can't update the newSize lesser than the original number of vacancy
					if((index.getClassSize() - newSize) > index.getVacancy()){
						System.out.println("New class size is too small for registered students!");
					}
					else{
						index.setClassSize(newSize);

						int len = index.getWaitlistLength();
						while(len > 0 && index.getVacancy() > 0){
							len--;

							Student newAdd = index.popWaitlist();
							if(newAdd.getNoOfAU() + index.getCourse().getNumOfAU() <= newAdd.getMaxAU()){
								newAdd.removeWaitlist(index);
								newAdd.addReg(index);
								index.addReg(newAdd);
								break;
							}
							else{
								index.addWaitlist(newAdd);
							}

						}
					}
					break;
				
				// ADD CLASS //
				case 3:
					if(index.getVacancy() != index.getClassSize()){
						System.out.println("This index already registered by someone, cannot add class.");
						break;
					}
					ArrayList<String> dayOfWeekList = new ArrayList<String>();
					Collections.addAll(dayOfWeekList,"MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY".split(","));
					System.out.print("Class ID: ");
					String classID = sc.nextLine();
					String type;
					while(true){
						System.out.print("Type of class (LEC/TUT/LAB/SEM): ");
						type = sc.nextLine().toUpperCase();
						if(type.equals("LEC")||type.equals("TUT")||type.equals("LAB")||type.equals("SEM"))break;
						else System.out.print("Please enter LEC/TUT/LAB/SEM! ");
					}
					Date startTime;
					Date endTime;
					while(true){
						while(true){
							System.out.print("Start time (In 24Hr format, eg.15:30): ");
							String strStartTime = sc.nextLine();
							try {
								final SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
								startTime = sdf.parse(strStartTime);
								break;
							} catch (final ParseException e) {
								System.out.println("Please follow the 24Hr format (08:30 / 14:45)!");
							}
						}
						while(true){
							System.out.print("End time (In 24Hr format, eg.18:30): ");
							String strEndTime = sc.nextLine();
							try {
								final SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
								endTime = sdf.parse(strEndTime);
								break;
							} catch (final ParseException e) {
								System.out.println("Please follow the 24Hr format (08:30 / 14:45)!");
							}
						}
						if(endTime.compareTo(startTime)<0){
							System.out.println("***Error: End time is earlier than start time!***");
							System.out.println();
						}
						else break;
				}
					System.out.print("Venue: ");
					String venue = sc.nextLine();
					System.out.print("Group number: ");
					String groupNo = sc.nextLine();
					System.out.print("Week(ODD, EVEN, BOTH): ");
					String week = sc.nextLine();
					String dayOfWeek;
					while(true){
						System.out.print("Day of week (Monday, Tuesday...): ");
						dayOfWeek = sc.nextLine().toUpperCase();
						if (dayOfWeekList.contains(dayOfWeek))break;
						else{
							System.out.println("Please enter the correct format for day of week!");
						}
					}
					Class temClass = new Class(classID, type, startTime, endTime, venue, groupNo, week, dayOfWeek);
					index.addClass(temClass);
					break;
				
				// REMOVE CLASS //
				case 4:
					if(index.getVacancy() != index.getClassSize()){
						System.out.println("This index already registered by someone, cannot remove class.");
						break;
					}
					System.out.println("Class ID to be dropped: ");
					tem = sc.nextLine();
	
					for(Class c : index.getClasses()){
						if(c.getClassID().equals(tem)){
							index.removeClass(c);
							break;
						}
						else
							System.out.println("Class ID doesn't exist!");
					}
					break;
				
				// BACK TO UPDATE COURSE PAGE //
				case 5:
                    System.out.println("Returning to update course page...");
                    break;
                default:
                    System.out.println("Wrong Input!!");
                    break;
			}
			
			// Saves the data immediately after an operation.
			System.out.println("Saving data ...");
			mainApp.saveData();
		}
	}
	
	/**
	 * Method that prints out all the indexes of a Course.
	 */
	public void listIndex() {
		System.out.println("Indexes available: ");
		for (Index i: indexes){
			System.out.println(i.getIndexNo());
		}
	}

}
