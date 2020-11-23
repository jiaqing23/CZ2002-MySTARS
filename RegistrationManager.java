import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a course Registration Manager that process the logic and flow of course registration.
 * Registration Manager is a Control Class.
 */
public class RegistrationManager implements Serializable{

    /**
     * Static method that checks whether an Index is clashed with any classes of a registered Index by a Student.
     * @param student Student object that is being checked. 
     * @param index Index that is being checked.
     * @return Return true when there is a clash, else return false.
     */
    public static boolean isClash(Student student, Index index){
        
        ArrayList<Index> indexes = new ArrayList<Index>();
        indexes.addAll(student.getRegistered());
        indexes.addAll(student.getWaitlist());

        for(Index anotherIndex: indexes){
            for(Class class1: index.getClasses()){
                for(Class class2: anotherIndex.getClasses()){
                    if(class1.clash(class2)) return true;
                }
            }
        }
        
        return false;
    }

    /**
     * Static method that checks whether an Index is already being registered (having waitlist, registered status) by a Student.
     * @param student Student object that is being checked.
     * @param index Index that is being checked.
     * @return Return true when there is a repeat registration, else return false.
     */
    public static boolean isRepeated(Student student, Index index){
        
        ArrayList<Index> indexes = new ArrayList<Index>();
        indexes.addAll(student.getRegistered());
        indexes.addAll(student.getWaitlist());

        for(Index anotherIndex: indexes){
            if(index.getCourse() == anotherIndex.getCourse()) return true;
        }
        
        return false;
    }

    /**
     * Static method that checks whether an Index is in a waitlist of a Student.
     * @param student Student object that is being checked.
     * @param index Index to be checked.
     * @return
     */
    public static boolean isInWaitlist(Student student, Index index){
        return student.getWaitlist().contains(index);
    }
    
    /**
     * Static method that checks whether an Index is registered by a Student.
     * @param student Student object that is being checked.
     * @param index Index to be checked.
     * @return
     */
    public static boolean isRegistered(Student student, Index index){
        return student.getRegistered().contains(index);
    }

    /**
     * Static method that process the request of Index registration by a Student.
     * @param student Student object that requests a Index registration.
     * @param index Index to be registered.
     * @return Return true when Index is successfully registered, else return false.
     */
    public static boolean processAdd(Student student, Index index){
        if(isRepeated(student, index)){
            System.out.println("You have already taken this course!");
            return false;
        }

        if(isClash(student, index)){
            System.out.println("This index's timeslot clashes with your other registered indexes / indexes in waitlist!");
            return false;
        }

        if(isRegistered(student, index)){
            System.out.println("You have already registered this index!");
            return false;
        }

        if(isInWaitlist(student, index)){
            System.out.println("This index is in your waitlist!");
            return false;
        }

        if(index.getVacancy() > 0 && student.getMaxAU() >= student.getNoOfAU() + index.getCourse().getNumOfAU()){
            index.addReg(student);
            student.addReg(index);
            System.out.println("Succesfully registered " + index.getCourse().getCourseCode() + " " + index.getCourse().getCourseName());
            System.out.println("Index: " + index.getIndexNo());
        }
        else{
            index.addWaitlist(student);
            student.addWaitlist(index);
            System.out.println("Succesfully registered " + index.getCourse().getCourseCode() + " " + index.getCourse().getCourseName());
            System.out.println("Index: " + index.getIndexNo() + " into waitlist");
        }

        return true;
    }

    /**
     * Static method that prcoess the request of Index deregistration by a Student.
     * @param student Student object that requests a Index deregistration.
     * @param index Index to be deregistered.
     */
    public static void processDrop(Student student, Index index){
        if(isInWaitlist(student, index)){
            student.removeWaitlist(index);
            index.removeWaitlist(student);
            System.out.println("Succesfully deregistered " + index.getCourse().getCourseCode() + " " + index.getCourse().getCourseName());
            System.out.println("Index: " + index.getIndexNo());
            
        }
        //Need to make sure that the student has registered for the index before dropping
        else if(isRegistered(student, index)){
            Student newAdd;
            index.removeReg(student);
            student.removeReg(index);

            int len = index.getWaitlistLength();
            while(len > 0){
                len--;

                newAdd = index.popWaitlist();
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
            System.out.println("Succesfully deregistered " + index.getCourse().getCourseCode() + " " + index.getCourse().getCourseName());
            System.out.println("Index: " + index.getIndexNo());
        }
        else{
            System.out.println("You haven't registered for this index!");
        }
    }

    /**
     * Static method that process the request of swapping Index registered by two Student.
     * @param sourceInd Source registered Index of a Student.
     * @param desInd Target registered Index of another Student.
     * @param sourceStudent Source Student object.
     * @param desStudent Target Student object.
     */
    public static void processSwap(Index sourceInd, Index desInd, Student sourceStudent, Student desStudent){

        if(desInd.getCourse() != sourceInd.getCourse()){
            System.out.println("Two index are not same course");
            return;
        }
        
        //Unable to change if the students has not registered for the index or still in waitlist
        if(!isRegistered(sourceStudent, sourceInd)){
            System.out.println("You have not registered for this index or it is still in your waitlist!");
            return;
        }
     
        if(!isRegistered(desStudent, desInd)){
            System.out.println("Your friend don't have this index in registered course list.");
            return;
        }

        //Checking whether there will be any clashes after swapping the indexes
        if(isClash(sourceStudent, sourceInd)){
            System.out.println("This index clash with other indexes you registered!");
            return;
        }

        if(isClash(desStudent, desInd)){
            System.out.println("This index clash with other indexes your friend registered!");
            return;
        }
        
        sourceStudent.removeReg(sourceInd);
        sourceStudent.addReg(desInd);
        desStudent.removeReg(desInd);
        desStudent.addReg(sourceInd);
        sourceInd.removeReg(sourceStudent);
        sourceInd.addReg(desStudent);
        desInd.removeReg(desStudent);
        desInd.addReg(sourceStudent);
    }

    /**
     * Static method that process the request of changing Index registered by a Student.
     * @param student Student object that requests a change of Index.
     * @param sourceInd Source Index that has been registered.
     * @param desInd Target Index intended to be changed to.
     */
    public static void processChangeIndex(Student student, Index sourceInd, Index desInd){

        int waitlist = 0;
        if(isInWaitlist(student, sourceInd)){
            student.removeWaitlist(sourceInd);
            waitlist = 1;
        }
        else if(isRegistered(student, sourceInd)){
            student.removeReg(sourceInd);
        }
        else{
            System.out.println("You haven't registered for this index!");
            return;
        }

        boolean valid = processAdd(student, desInd);

        if(waitlist == 1){
            student.addWaitlist(sourceInd);
        }
        else{
            student.addReg(sourceInd);
        }

        if(valid){
            processDrop(student, sourceInd);
            System.out.println("Changed successfully!");
        }
    }
}
