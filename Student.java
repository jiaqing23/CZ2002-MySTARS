import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Represent a Student that uses the MySTARS system application.
 * A Student is an user, therefore it inherits the User class.
 */
public class Student extends User{
	/**
	 * The number of academic units registered by a Student. Default value is 0.
	 */
	private int noOfAU = 0;

	/**
	 * The maximum number of academic units that can be registered by a Student. Default value is 21 before overloading.
	 */
	private int maxAU = 21;

	/**
	 * The gender of a Student.
	 */
	private String gender;

	/**
	 * The nationality of a Student.
	 */
	private String nationality;

	/**
	 * The matriculation number of a Student.
	 */
	private String matricNumber;

	/**
	 * The email account of a Student.
	 */
	 private String email;

	/**
	 * The list of registered indexes of a Student.
	 */
	private ArrayList<Index> registered = new ArrayList<Index>();

	/**
	 * The list of registered indexes of a Student that are being placed in waitlist.
	 */
	private ArrayList<Index> waitlist = new ArrayList<Index>();

	/**
	 * Creates a Student with the given parameters.
	 * @param name The full name of this Student. The full name should include first name and last name.
	 * @param username The account username of this Student.
	 * @param maxAU The maximum number of academic units that can be registered by this Student.
	 * @param gender The gender of this Student.
	 * @param nationality The nationality of this Student.
	 * @param matricNumber The matriculation number of this Student.
	 * @param email The email account of this Student.
	 */
	public Student(String name, String username, int maxAU, String gender, String nationality, String matricNumber, String email) {
		super(name, username);
		this.maxAU = maxAU;
		this.gender = gender;
		this.nationality = nationality;
		this.matricNumber = matricNumber;
		this.email = email;
	}

	/**
	 * Method that increases the number of academic units registered by a Student.
	 * @param AU Number of academic units to be increased to the calling Student object.
	 */
	public void setNoOfAU(int AU) {
		noOfAU += AU;
	}
	
	/**
	 * Method that gets the number of academic units register by a Student.
	 * @return Number of academic units registered by the calling Student object.
	 */
	public int getNoOfAU() {
		return noOfAU;
	}

	/**
	 * Method that changes the maximum number of academic units that can be registered by a Student.
	 * @param AU The new maximum number of academic units that can be registered by the calling Student object.
	 */
	public void setMaxAU(int AU) {
		maxAU = AU;
	}
	
	/**
	 * Method that gets the maximum number of academic units that can be registered by a Student.
	 * @return The maximum number of academic units that can be registered by the calling Student object.
	 */
	public int getMaxAU() {
		return maxAU;
	}

	/**
	 * Method that changes the gender of a Student.
	 * @param gender The new gender of the calling Student object.
	 */
	public void setGender(String gender) {
		this.gender=gender;
	}
	
	/**
	 * Method that gets the gender of a Student.
	 * @return The gender of the calling Student object.
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Method that changes the nationality of a Student.
	 * @param nationality The new nationality of the calling Student object.
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	/**
	 * Method that gets the nationality of a Student.
	 * @return The nationality of the calling Student object.
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * Method that changes the matriculation number of a Student.
	 * @param matricNumber The new matriculation number of the calling Student object.
	 */
	public void setMatriculationNumber(String matricNumber) {
		this.matricNumber = matricNumber;
	}
	
	/**
	 * Method that gets the matriculation number of a Student.
	 * @return The matriculation number of the calling Student object.
	 */
	public String getMatricNumber() {
		return matricNumber;
	}

	/**
	 * Method that changes the email of a Student.
	 * @param email New email of the calling Student object.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Method that gets the email of a Student.
	 * @return Email of the calling Student object.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Method that gets the list of registered indexes of a Student.
	 * @return The list of registered indexes of the calling Student object.
	 */
	public ArrayList<Index> getRegistered(){
		return registered;
	}
	
	/**
	 * Method that gets the list of registered indexes of a Student that are placed in waitlist.
	 * @return The list of registered indexes of the calling Student object that are placed in waitlist.
	 */
	public ArrayList<Index> getWaitlist(){
		return waitlist;
	}
	
	/**
	 * Method that adds an Index to the registered Index list of a Student.
	 * The number of registered academic units of a Student is increased at the end of the method.
	 * @param index The Index object to be added to the registered Index list of the calling Student object.
	 */
	public void addReg(Index index) {
		registered.add(index);
		noOfAU += index.getCourse().getNumOfAU();
	}
	
	
	/**
	 * Method that removes an Index from the registered Index list of a Student.
	 * The number of registered academic units of a Student is decreased at the end of the method.
	 * @param index The Index object to be removed from the registered Index list of the calling Student object.
	 */
	public void removeReg(Index index) {
		registered.remove(index);
		noOfAU -= index.getCourse().getNumOfAU();
	}

	/**
	 * Method that adds an Index to the registered Index list in waitlist status of a Student.
	 * @param index The Index object to be added to the registered Index list with waitlist status of the calling Student object.
	 */
	public void addWaitlist(Index index) {
		waitlist.add(index);
	}

	/**
	 * Method that removes an Index from the registered Index list in waitlist status of a Student.
	 * @param index The Index object to be removed from the registered Index list with waitlist status of the calling Student object.
	 */
	public void removeWaitlist(Index index) {
		waitlist.remove(index);
	}
	
	/**
	 * Method that process the registration of an Index by a Student.
	 * The process is brought out by the RegistrationManager class and it is a lengthy legal process.
	 * @param index Index to be registered by the calling Student object.
	 */
	public void addIndex(Index index) {
		RegistrationManager.processAdd(this, index);
	}
	
	/**
	 * Method that process the deregistration of an Index by a Student.
	 * The process is brought out by the RegistrationManager class and it is a lengthy legal process.
	 * @param index Index to be dropped by the calling Student object.
	 */
	public void dropIndex(Index index) {
		RegistrationManager.processDrop(this, index);
	}

	/**
	 * Method that process the change of Index by a Student.
	 * * The process is brought out by the RegistrationManager class and it is a lengthy legal process.
	 * @param sourceInd Index object to be changed by the calling Student object.
	 * @param desInd Target Index object to be changed to by the calling Student object.
	 */
	public void changeIndex(Index sourceInd, Index desInd) {
		RegistrationManager.processChangeIndex(this, sourceInd, desInd);		
	}
	
	/**
	 * Method that process the swap of Index by a Student with another Student.
	 * * * The process is brought out by the RegistrationManager class and it is a lengthy legal process.
	 * @param sourceInd Index object to be swapped by the calling Student object.
	 * @param desInd Target Index object to be swapped by the calling Student object.
	 * @param desID Target Student object to be swapped with by the calling Student object.
	 */
	public void swapIndex(Index sourceInd, Index desInd, Student desID) {
		RegistrationManager.processSwap(sourceInd, desInd, this, desID);
	}

	/**
	 * Method that prints all the registered course timetable of a Student.
	 */
	public void printTimetable() {
		if (registered.isEmpty()){
			System.out.println("You haven't registered any course!");
			return;
		}
		
		System.out.println("Registered courses timetable: ");
		ArrayList<Class> classes = new ArrayList<Class>();
		HashMap<Class, Index> classToIndex = new HashMap<Class, Index>(); 
		for(Index i : registered){
			for(Class c: i.getClasses()){
				classes.add(c);
				classToIndex.put(c, i);
			}
		}

		Collections.sort(classes, new SortbyDatetime());
		
		String[] weekday = { "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY" };
		ArrayList<ArrayList<Class>> classesByWeekday = new ArrayList<>(7);
		for(int i = 0; i < 7; i++) {
			classesByWeekday.add(new ArrayList());
		}
		for(int i = 0; i < 7; i++){
			for(Class c: classes){
				if(c.getDayOfWeek().equals(weekday[i])){
					classesByWeekday.get(i).add(c);
				}
			}
		}

		String alignFormat = "| %-11s | %-12s | %-11s | %-11s | %-10s | %-12s | %-9s | %-6s |%n";
		System.out.format("+-------------+--------------+-------------+-------------+------------+--------------+-----------+--------+%n");
		System.out.format("|   Weekday   |     Time     | Course Code |    Index    | Class Type | Group Number |   Venue   |  Week  |%n");
		System.out.format("+-------------+--------------+-------------+-------------+------------+--------------+-----------+--------+%n");

		for(int j = 0; j< 7; j++){
			ArrayList<Class> ar = classesByWeekday.get(j);
			if(ar.isEmpty()){
				System.out.format(alignFormat, weekday[j], "", "", "", "", "", "", "");
			}
			boolean first = true;
			for(Class c: ar){

				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				String tem1 = sdf.format(c.getStartTimePeriod());
				String tem2 = sdf.format(c.getEndTimePeriod());
				Index i = classToIndex.get(c);
				System.out.format(alignFormat, first?weekday[j]:"", tem1+"-"+tem2, i.getCourse().getCourseCode(), i.getIndexNo(),
									c.getType(), c.getGroup(), c.getVenue(), c.getWeek());
				first = false;
			}
			
		
			
		}
		System.out.format("+-------------+--------------+-------------+-------------+------------+--------------+-----------+--------+%n");

		System.out.println("%nRegistered Course:%n");
		for(Index i: registered) System.out.println(i.getCourse().getCourseCode()+"\t"+i.getIndexNo());
	}
}
