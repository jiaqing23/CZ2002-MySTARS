import java.io.Serializable;
import java.util.ArrayList;

public class Student extends User implements Serializable{
	private int noOfAU = 0;
	private int maxAU = 21;//Student can register for maximum of 21 AUs before overloading
	private String gender;
	private String nationality;
	private String matricNumber;
	private ArrayList<Index> registered = new ArrayList<Index>();
	private ArrayList<Index> waitlist = new ArrayList<Index>();

	// CONSTRUCTOR
	public Student(String name, String username, int maxAU, String gender, String nationality, String matricNumber) {
		super(name, username);
		this.maxAU = maxAU;
		this.gender = gender;
		this.nationality = nationality;
		this.matricNumber = matricNumber;
	}

	// SET AND GET METHODS
	public void setNoOfAU(int AU) {
		noOfAU += AU;
	}
	
	public int getNoOfAU() {
		return noOfAU;
	}

	public void setMaxAU(int AU) {
		maxAU = AU;
	}
	
	public int getMaxAU() {
		return maxAU;
	}

	public void setGender(String gender) {
		this.gender=gender;
	}
	
	public String getGender() {
		return gender;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	public String getNationality() {
		return nationality;
	}

	public void setMatriculationNumber(String matricNumber) {
		this.matricNumber = matricNumber;
	}
	
	public String getMatriculationNumber() {
		return matricNumber;
	}

	public ArrayList<Index> getRegistered(){
		return registered;
	}
	
	public ArrayList<Index> getWaitlist(){
		return waitlist;
	}
	
	// CLASS METHODS
	public int checkVacancy(Index index) {
		return index.getVacancy();
	}
	
	//Add an index to regirstered index
	public void addReg(Index index) {
		registered.add(index);
		noOfAU += index.getCourse().getNumOfAU();
	}
	
	//Remove a registered index
	public void removeReg(Index index) {
		registered.remove(index);
		noOfAU -= index.getCourse().getNumOfAU();
	}

	//Add an index into waitlist
	public void addWaitlist(Index index) {
		waitlist.add(index);
	}

	//Remove an index from waitlist
	public void removeWaitlist(Index index) {
		waitlist.remove(index);
	}
	
	//Adding a new index
	public void addIndex(Index index) {
		RegistrationManager.processAdd(this, index);
	}
	
	//Dropping a registered index
	public void dropIndex(Index index) {
		RegistrationManager.processDrop(this, index);
	}

	//Change a registered index to another index
	public void changeIndex(Index sourceInd, Index desInd) {
		RegistrationManager.processChangeIndex(this, sourceInd, desInd);		
	}
	
	//Used to swap index with another student
	public void swapIndex(Index sourceInd, Index desInd, Student desID) {
		RegistrationManager.processSwap(sourceInd, desInd, this, desID);
	}

	//Print all indexes registered by the student
	public void printIndex() {
		System.out.println("Registered indexes: ");
		for(Index i : registered){
			System.out.println(i.getIndexNo());
		}

	}
	
}
