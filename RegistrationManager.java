import java.util.ArrayList;

public class RegistrationManager {

    public static boolean isClash(Student student, Index index){

        ArrayList<Index> indexes = new ArrayList<Index>();
        indexes.addAll(student.getRegistered());
        indexes.addAll(student.getWaitlist());

        for(Index anotherIndex: indexes){
            for(Class class1: index.getClassess()){
                for(Class class2: anotherIndex.getClassess()){
                    if(class1.clash(class2)) return true;
                }
            }
        }
        return false;
    }

    public static boolean isInWaitlist(Student student, Index index){
        for(Index anotherIndex: student.getWaitlist()){
            if(index.equals(anotherIndex)) return true;
        }

        return false;
    }
    
    public static boolean isRegistered(Student student, Index index){
        for(Index anotherIndex: student.getRegistered()){
            if(index.equals(anotherIndex)) return true;
        }

        return false;
    }

    public static void processAdd(Student student, Index index){
        if(isClash(student, index)){
            System.out.println("This index clash with other indexes you registered or added into waitlist!");
            return;
        }

        if(isRegistered(student, index)){
            System.out.println("You have already registered waitlist!");
            return;
        }

        if(isInWaitlist(student, index)){
            System.out.println("This index is in your waitlist!");
            return;
        }

        if(index.getVacancy()){
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
            index.removeReg(student);
        }
    }
}
