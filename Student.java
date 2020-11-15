import java.util.ArrayList;

public class Student extends User {

	private int noOfAU = 0;
	private int maxAU;
	private String gender;
	private String nationality;
	private String matricNumber;
	private ArrayList<Index> registered = new ArrayList<Index>();
	private ArrayList<Index> waitlist = new ArrayList<Index>();

	// CONSTRUCTOR
	public Student(int maxAU, String gender, String nationality, String matricNumber) {
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
		matriculationNumber = matricNumber;
	}
	
	public String getMatriculationNumber() {
		return matriculationNumber;
	}

	public ArrayList<Index> getRegistered(){
		return registered;
	}
	
	public ArrayList<Index> getWaitlist(){
		return waitlist;
	}
	
	// CLASS METHODS
	public boolean checkIndex(Index index) {
		return (registered.contains(index) || waitlist.contains(index));
	}
	
	public boolean addIndex(Index index) {
		
		if (checkIndex(index)) {
			System.out.println("The index has already been registered before! ");
			return false;
		}

		// call processAdd() of RegistrationManager class
		// return true;
	
	}
	
	public boolean dropIndex(Index index) {
		
		if (checkIndex(index)) {
			if(registered.contains(index))
				removeReg(index);
			else if(waitlist.contains(index))
				removeWaitlist(index);

			return processDrop(index);
		}
		else {
			System.out.println("Unable to drop an unregistered index!");
			return false;
		}
	}

	public void printIndex() {

		System.out.println("Registered indexes: ");
		for(Index i : registered){
			System.out.println(i.getIndexNo());
		}

	}
	
	public int checkVacancy(Index index) {
		return index.getVacancy();
	}
	
	public void addReg(Index index) {
		registered.add(index);
	}
	
	public void removeReg(Inde index) {
		registered.remove(index);
	}

	public void addWaitlist(Index index) {
		waitlist.add(index);
	}

	public void removeWaitlist(Index index) {
		waitlist.remove(index);
	}

	public boolean changeIndex(Index sourceInd, Index desInd) {
		
		if(!checkIndex(sourceInd)) {
			System.out.println("Index " + sourceInd + " is not registered before!");
			return false;
		}
		
		// call processSwap() of RegistrationManager class
		// return true;
		
	}
	
	
	
	public boolean swapIndex(Index sourceInd, Index desInd, Student sourceID, Student desID) {
		
		boolean checkSource = sourceID.checkIndex(sourceInd);
		boolean checkDes = desID.checkIndex(desInd);
		
		if(!checkSource){
			System.out.println("Student "+ sourceID+ " has not registered for "+sourceInd+"!");
			return false;
		}
		
		if(!checkDes){
			System.out.println("Student "+ desID+" has not registered for "+desInd+"!");
			return false;
		}
		
		if(checkSource && checkDes){
			// call processSwap() of RegistrationManager class
			// return true;
		}
	}
	
}
