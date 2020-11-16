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

    public static void processAdd(Student student, Index index){
        if(isClash(student, index)){
            System.out.println("This index clash with other indexes you registered or added into waitlist!");
            return;
        }

        if(isRegistered(student, index)){
            System.out.println("You have already registered or add !");
            return;
        }

        if(isInWaitlist(student, index)){
            System.out.println("This index is in your waitlist!");
            return;
        }

        if(index.getVacancy() > 0){
            index.addReg(student);
            student.addReg(index);
        }
        else{
            index.addWaitlist(student);
            student.addWaitlist(index);
        }
    }

    public static void processDrop(Student student, Index index){
        if(isInWaitlist(student, index)){
            index.removeWaitlist(student);
        }

        if(isRegistered(student, index)){
            Student newAdd = index.removeReg(student);
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
        
        sourceStudent.removeReg(sourceInd);
        sourceStudent.addReg(desInd);
        desStudent.removeReg(desInd);
        desStudent.addReg(sourceInd);
    }
}
