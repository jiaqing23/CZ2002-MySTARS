import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Date;

public class Admin extends User{
    MySTARS mainApp;

    public Admin(String name, String username){
        super(name, username);
    }
    
    public void editPeriod(Date start, Date end){
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
        Course course;
        for(Course c: mainApp.courses){
            if(c.courseCode == courseCode){
                course = c;
                break;
            }
        }

        System.out.println("Updating course " + courseCode);
        System.out.println("(1) Update Course Code");
        System.out.println("(2) Update Course Name");
        System.out.println("(3) Update School");
        System.out.println("(4) Add Index");
        System.out.println("(5) Update Index");
        System.out.println("(6) Remove Index");
        System.out.println("Select Option: ");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        sc.next(); 
        String tem;   
        switch(option){
            case 1:
                tem = sc.nextLine();
                course.setCode(tem);
                break;
            case 2:
                tem = sc.nextLine();
                course.setName(tem);
                break;
            case 3:
                tem = sc.nextLine();
                course.setSchool(tem);
                break;
            case 4:
                course.addIndex();
                break;
            case 5:
                course.updateIndex();
                break;
            case 6:
                course.dropIndex();
                break;
            default:
                System.out.println("Invalid option!");
                break;
        }
    }

    public void checkVacancy(Index index){
        System.out.printf("Index %s have $d slots left.\n", index.getIndexNo(), index.getVacancy());
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
