import java.util.ArrayList;

public class RegistrationManager implements Serializable{

    public static boolean isClash(Student student, Index index){
        /*
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
        */
        return false;
    }

    public static boolean isInWaitlist(Student student, Index index){
        return student.getWaitlist().contains(index);
    }
    
    public static boolean isRegistered(Student student, Index index){
        return student.getRegistered().contains(index);
    }

    public static boolean processAdd(Student student, Index index){
        if(isClash(student, index)){
            System.out.println("This index clash with other indexes you registered or added into waitlist!");
            return false;
        }

        if(isRegistered(student, index)){
            System.out.println("You have already registered or add!");
            return false;
        }

        if(isInWaitlist(student, index)){
            System.out.println("This index is in your waitlist!");
            return false;
        }

        if(index.getVacancy() > 0){
            index.addReg(student);
            student.addReg(index);
        }
        else{
            index.addWaitlist(student);
            student.addWaitlist(index);
        }

        return true;
    }

    public static void processDrop(Student student, Index index){
        if(isInWaitlist(student, index)){
            index.removeWaitlist(student);
        }

        if(isRegistered(student, index)){
            Student newAdd;
            index.removeReg(student);
            newAdd = index.popWaitlist();
            if(newAdd != null){
                newAdd.dropIndex(index);
                newAdd.addReg(index);
            }
        }
    }

    public static void processSwap(Index sourceInd, Index desInd, Student sourceStudent, Student desStudent){
        if(!isRegistered(sourceStudent, sourceInd)){
            System.out.println("You have not registered for this index or it is still in your waitlist!");
            return;
        }
        
        if(!isRegistered(desStudent, desInd)){
            System.out.println("Your friend don't have this index.");
            return;
        }

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

    public static void processChangeIndex(Student student, Index sourceInd, Index desInd, boolean inWait){

        if(inWait){
            student.removeWaitlist(sourceInd);
        }
        else{
            student.removeReg(sourceInd);
        }

        boolean valid = processAdd(student, desInd);

        if(inWait){
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

    //Todo: check AU, check date
}
