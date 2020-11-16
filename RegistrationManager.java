package stars_proj;

import java.util.ArrayList;
import java.util.Iterator;
public class RegistrationManager {
	
	public static boolean processClash(Student student, Index index) {
		//check if the index clashes with the student's current registered/waitlist classes
		ArrayList<Index> registered=student.getRegistered();
		ArrayList<Index> waitlist=student.getWaitlist();
		ArrayList<Class> myClasses = index.getClasses();
		Iterator<Index> iter1 = registered.iterator();
		Iterator<Index> iter2 = waitlist.iterator();
		Iterator<Class> iterMyClasses = myClasses.iterator();
		while(iter1.hasNext()) {
			ArrayList<Class>classes = iter1.next().getClasses();
			Iterator<Class> iter =  classes.iterator();
			while(iter.hasNext())
				while(iterMyClasses.hasNext())
					if (checkClash(iter.next(),iterMyClasses.next()))
						return true;//function to check if the two time slot clashes-->clash-->break,return true
		}
		while(iter2.hasNext()) {
			ArrayList<Class>classes = iter2.next().getClasses();
			Iterator<Class> iter =  classes.iterator();
			while(iter.hasNext())
				while(iterMyClasses.hasNext())
					if (checkClash(iter.next(),iterMyClasses.next()))
						return true;//function to check if the two time slot clashes-->clash-->break,return true
		}
		//no clashes detected:
		return false;
		
	}
	private static boolean checkClash(Class class1, Class class2) {
		return true;//how to check?!!!?
	};
	public static boolean processAdd(Student student, Index index) {//update on both sides: student&index???
		if(processClash(student,index))
			return false;
		if(index.getVacancy()>0) {//vacancy>0---add to registered directly
			index.addReg(student);
			student.addIndex(index);
		}
		else//else add to waitlist
		{
			index.updateWaitlist(student);
			student.updateWaitlist(index);
		}
		return true;//?
		
	}
	public static boolean processDrop(Student student, Index index) {
		if(processClash(student,index))
			return false;
		index.dropReg(student);
		student.dropIndex(index);
		if(index.getVacancy()>0) {//vacancy>0---add to registered directly
			if (!index.getWaitlist().isEmpty())//if not empty, update waitlist automatically
			//remove head from the queue (poll)
			//remove index from student's waitlist list
			//add the what was polled from the queue to index registered
			//add to student's registered
		}
		
		return true;//?
	}
}
