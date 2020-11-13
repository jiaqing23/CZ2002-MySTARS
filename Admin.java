import java.util.*;

public class Admin extends User{
    MySTARS mainApp;

    public Admin(String name, String username){
        super(name, username);
    }
    
    public void editPeriod(Calendar start, Calendar end){
        Period period = mainApp.getPeriod();
        period.setPeriod(start, end);
    }

    public void addStudent(String name, String username, String password, int maxAU, String gender, String nationality){
        if(PasswordManager.addAccount(username, password)){
            Student newStudent = new Student(name, username, maxAU, gender, nationality);
            mainApp.addStudent(newStudent);
            System.out.println("Student added!");
        }
        else{
            System.out.println("Username already exists!");
        }
    }

    public void addCourse(String school, String courseCode, String courseName, int numOfAU){
        Course course = new Course(school, courseCode, courseName, numOfAU);
        mainApp.courses.add(course);
    }

    public void updateCourse(String courseCode){
        
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
