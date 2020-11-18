import java.io.Serializable;
import java.util.ArrayList;

public class RegistrationManager implements Serializable{

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

    public static boolean isRepeated(Student student, Index index){
        
        ArrayList<Index> indexes = new ArrayList<Index>();
        indexes.addAll(student.getRegistered());
        indexes.addAll(student.getWaitlist());

        for(Index anotherIndex: indexes){
            if(index.getCourse() == anotherIndex.getCourse()) return true;
        }
        
        return false;
    }

    //Check whether the index is in student's waitlist
    public static boolean isInWaitlist(Student student, Index index){
        return student.getWaitlist().contains(index);
    }
    
    //Check whether the index is in student's registered index
    public static boolean isRegistered(Student student, Index index){
        return student.getRegistered().contains(index);
    }

    //Process adding of index
    public static boolean processAdd(Student student, Index index){
        if(isRepeated(student, index)){
            System.out.println("You took other index of this course!");
            return false;
        }

        if(isClash(student, index)){
            System.out.println("This index clash with other indexes you registered or added into waitlist!");
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
        }
        else{
            index.addWaitlist(student);
            student.addWaitlist(index);
        }

        return true;
    }

    //Process dropping of index
    public static void processDrop(Student student, Index index){
        if(isInWaitlist(student, index)){
            student.removeWaitlist(index);
            index.removeWaitlist(student);
        }

        //Need to make sure that the student has registered for the index before dropping
        if(isRegistered(student, index)){
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
                    break;
                }
                else{
                    index.addWaitlist(newAdd);
                }

            }
        }
    }

    //Method used when student wants to change index with friend
    public static void processSwap(Index sourceInd, Index desInd, Student sourceStudent, Student desStudent){
        //Unable to change if the students has not registered for the index or still in waitlist
        if(!isRegistered(sourceStudent, sourceInd)){
            System.out.println("You have not registered for this index or it is still in your waitlist!");
            return;
        }
     
        if(!isRegistered(desStudent, desInd)){
            System.out.println("Your friend don't have this index.");
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

        if(desInd.getCourse() != sourceInd.getCourse()){
            System.out.println("Two index are not same course");
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

    //Method used when the student wants to change index
    public static void processChangeIndex(Student student, Index sourceInd, Index desInd){

        if(isInWaitlist(student, sourceInd)){
            student.removeWaitlist(sourceInd);
        }
        else{
            student.removeReg(sourceInd);
        }

        boolean valid = processAdd(student, desInd);

        if(isInWaitlist(student, sourceInd)){
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
