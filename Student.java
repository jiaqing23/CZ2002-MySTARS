import java.util.ArrayList;

public class Student extends User{
	private int noOfAU=0;
	private int maxAU;
	private String gender;
	private String nationality;
	private String matriculationNumber;
	private ArrayList<Index> registered;
	private ArrayList<Index> waitlist;
	
	//Use to check whether a student have registered for a specific index or not
	public boolean checkRegIndex(Index index) {
		return registered.contains(index);
	}
	
	
	public boolean addIndex(Index index) {
		
		if (this.checkRegIndex(index)) {
			System.out.println("The index has already been registered before! ");
			return false;
		}
		
		waitlist.add(index);
		System.out.println("The index has been added into waitlist! ");
		return true;
		
	}
	
	
	
	public boolean dropIndex(Index index) {
		
		if (this.checkRegIndex(index)) {
			//how to call
			return processDrop(index);
		}
		else {
			System.out.println("Unable to drop an unregistered index!");
			return false;
		}
	}

	
	
	public void printIndex() {
		System.out.println("Registered indexes: ");
		for (int i = 0; i < registered.size(); i++) {
			System.out.println(registered.get(i));
		}
	}
	
	
	
	public int checkVacancy(Index index) {
		return index.getVacancy();
	}
	
	
	
	public boolean changeIndex(Index sourceInd, Index desInd) {
		
		int i;
		boolean found=false;
		
		for (i = 0; i<registered.size(); i++) {
			if (registered.get(i) == sourceInd) {
				found=true;
				break;
			}
		}
		
		if(!found) {
			System.out.println("Index "+sourceInd+" is not registered before!");
			return false;
		}
		
		//Assume changing of index can be done directly if the desired index has vacancy
		if (desInd.getVacancy()>0) {
			registered.set(i, desInd);
			desInd.addReg(this);
			sourceInd.dropReg(this);
			System.out.println("Successfully changed! ");
			return true;
		}
		else {
			System.out.println("No vacancy left! ");
			return false;
		}
		
	}
	
	
	
	public boolean swapIndex(Index sourceInd, Index desInd, Student sourceID, Student desID) {
		
		boolean checkSource = sourceID.checkRegIndex(sourceInd);
		boolean checkDes = desID.checkRegIndex(desInd);
		
		if(checkSource && checkDes) {
			System.out.println("Student "+ sourceID+ " has not registered for "+sourceInd+" and student "+ desID+" has not registered for "+desInd+"!");
			return false;
		}
		
		if (checkSource) {
			System.out.println("Student "+ sourceID+ " has not registered for "+sourceInd+"!");
			return false;
		}
		
		if(checkSource && checkDes) {
			System.out.println("Student "+ desID+" has not registered for "+desInd+"!");
			return false;
		}
		
		sourceID.dropIndex(sourceInd);
		desID.dropIndex(desInd);
		sourceID.addIndex(desInd);
		desID.addIndex(sourceInd);
		
		System.out.println("Successfully swapped!");
		return true;
		
	}
	
	public void addReg(Index index) {
		registered.add(index);
	}
	
	public void addWaitlist(Index index) {
		waitlist.add(index);
	}
	
	//Get and Set
	public ArrayList<Index> getRegistered(){
		return registered;
	}
	
	public void setRegistered(ArrayList<Index> registered) {
		this.registered=registered;
	}
	
	public ArrayList<Index> getWaitlist(){
		return waitlist;
	}
	
	public void setWaitlist(ArrayList<Index> waitlist) {
		this.waitlist = waitlist;
	}
	
	public void setMatriculationNumber(String matricNumber) {
		matriculationNumber = matricNumber;
	}
	
	public String getMatriculationNumber() {
		return matriculationNumber;
	}
	
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
	
}
