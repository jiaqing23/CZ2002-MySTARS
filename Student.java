import java.util.ArrayList;

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
	 */
	public Student(String name, String username, int maxAU, String gender, String nationality, String matricNumber) {
		super(name, username);
		this.maxAU = maxAU;
		this.gender = gender;
		this.nationality = nationality;
		this.matricNumber = matricNumber;
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
	public String getMatriculationNumber() {
		return matricNumber;
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
	 * Method that gets the vacancy of an Index.
	 * @param index The Index object that has the vacancy to be returned.
	 * @return The vacancy of the Index object being passed in as parameter. 
	 */
	public int checkVacancy(Index index) {
		return index.getVacancy();
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
	 * Method that prints all the registered indexes' index number of a Student.
	 */
	public void printIndex() {
		System.out.println("Registered indexes: ");
		for(Index i : registered){
			System.out.println(i.getIndexNo());
		}

	}
	
}
