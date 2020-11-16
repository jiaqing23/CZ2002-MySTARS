import java.io.Serializable;
import java.util.ArrayList;

public class Student extends User implements Serializable{
	private int noOfAU=0;
	private int maxAU;
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
	
	public void addReg(Index index) {
		registered.add(index);
	}
	
	public void removeReg(Index index) {
		registered.remove(index);
	}

	public void addWaitlist(Index index) {
		waitlist.add(index);
	}

	public void removeWaitlist(Index index) {
		waitlist.remove(index);
	}
	
	public void addIndex(Index index) {
		RegistrationManager.processAdd(this, index);
	}
	
	public void dropIndex(Index index) {
		RegistrationManager.processDrop(this, index);
	}

	public void changeIndex(Index sourceInd, Index desInd) {
		RegistrationManager.processChangeIndex(this, sourceInd, desInd);		
	}
	
	public void swapIndex(Index sourceInd, Index desInd, Student desID) {
		RegistrationManager.processSwap(sourceInd, desInd, this, desID);
	}

	public void printIndex() {

		System.out.println("Registered indexes: ");
		for(Index i : registered){
			System.out.println(i.getIndexNo());
		}

	}
	
}
