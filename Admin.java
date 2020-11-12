import java.util.*;

public class Admin extends User{
    MySTARS mainApp;

    public Admin(String name, String userID, String username){
        super(name, userID, username, isStaff);
    }
    
    public void editPeriod(Calendar start, Calendar end){
        Period period = mainApp.getPeriod();
        period.setPeriod(start, end);
    }

    public void addStudent(String name, String username, String password, String isStaff, ArrayList<Index>, int maxAU, String gender, String nationality){
        
    }

    public void addCourse(String school, String courseCode, String courseName, int numOfAU){
        //TBD
    }

    public void updateCourse(String courseCode){
        //TBD
    }

    public void checkVacancy(Index index){
        System.out.printf("%s have $d slots left.\n", index.getIndexNo(), index.getVacancy());
    }

    public void printByCourse(Course course){
        ArrayList<Index> indexes = course.getIndexes();
        for(Index index: indexes){
            ArrayList<Student> students = index.getRegisteredStud();
            for(Student student: students){
                System.out.printf("%s, %s, %s\n", student.getName(), student.getNationality(), student.getGender());
            }
        }
    }

    public void printByIndex(Index index){
        ArrayList<Student> students = index.getRegisteredStud();
            for(Student student: students){
                System.out.printf("%s, %s, %s\n", student.getName(), student.getNationality(), student.getGender());
        }
    }
}
