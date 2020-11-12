import java.util.*;

public class Admin extends User{
    
    public void editPeriod(Calendar start, Calendar end){
        //TBD
    }

    public void addStudent(String name, String username, String password, String isStaff, ArrayList<Index>, int maxAU, String gender, String nationality){
        //TBD
    }

    public void addCourse(String school, String courseCode, String courseName, int numOfAU){
        //TBD
    }

    public void updateCourse(String courseCode){
        //TBD
    }

    public int checkVacancy(Index index){
        //TBD
    }

    public void printByCourse(Course course){
        ArrayList<Index> indexes = course.getIndexes();
        for(Index index: indexes){
            ArrayList<Student> students = index.getRegisteredStud();
            for(Student student: students){
                System.out.printf("%s, %s, %s", student.getName(), student.getNationality(), student.getGender());
            }
        }
    }

    public void printByIndex(Index index){
        ArrayList<Student> students = index.getRegisteredStud();
            for(Student student: students){
                System.out.printf("%s, %s, %s", student.getName(), student.getNationality(), student.getGender());
        }
    }
}
