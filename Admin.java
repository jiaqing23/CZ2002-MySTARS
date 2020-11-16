import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

//inherited from User
public class Admin extends User{
    MySTARS mainApp;

    //Constructor 
    public Admin(String name, String username){
        super(name, username);
    }
    
    //Set the period of STARS
    public void editPeriod(Date start, Date end){
        Period period = mainApp.getPeriod();
        period.setPeriod(start, end);
    }

    //Add a new student
    public void addStudent(String name, String username, String password, int maxAU, String gender, String nationality, String matricNumber){
        if(PasswordManager.addAccount(username, password)){
            Student newStudent = new Student(name, username, maxAU, gender, nationality, matricNumber);
            mainApp.addStudent(newStudent);
            System.out.println("Student added!");
        }
        else{
            System.out.println("Username already exists!");
        }
    }

    //Add a new course
    public void addCourse(String school, String courseCode, String courseName, int numOfAU){
        Course course = new Course(school, courseCode, courseName, numOfAU);
        mainApp.courses.add(course);
    }

    //To update the attributes of a course
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
                System.out.print("New Course Code: ");
                tem = sc.nextLine();
                course.setCourseCode(tem);
                break;
            case 2:
                System.out.print("New Course Name: ");
                tem = sc.nextLine();
                course.setCourseName(tem);
                break;
            case 3:
                System.out.print("New School: ");
                tem = sc.nextLine();
                course.setSchool(tem);
                break;
            case 4:
                System.out.print("IndexNo to be added: ");
                //Need read more attributes(indexno, classsize, XXXX)
                tem = sc.nextLine();
                course.addIndex(tem); 
                break;
            case 5:
                System.out.print("IndexNo to be updated: ");
                tem = sc.nextLine();
                for(Index i: course.getIndexes()){
                    if(i.getIndexNo().equals(tem)){
                        course.updateIndex(i);
                        break;
                    }
                }
                break;
            case 6:
                System.out.print("IndexNo to be dropped: ");
                tem = sc.nextLine();
                for(Index i: course.getIndexes()){
                    if(i.getIndexNo().equals(tem)){
                        course.dropIndex(i);
                        break;
                    }
                }
                break;
            default:
                System.out.println("Invalid option!");
                break;
        }
    }

    //To check number of vacancy available in an index
    public void checkVacancy(Index index){
        System.out.printf("Index %s have $d slots left.\n", index.getIndexNo(), index.getVacancy());
    }

    //Print the name, nationaility and gender of students who registered for a certain course
    public void printByCourse(Course course){
        ArrayList<Index> indexes = course.getIndexes();
        for(Index index: indexes){
            ArrayList<Student> students = index.getRegisteredStud();
            for(Student student: students){
                System.out.printf("%s, %s, %s\n", student.getName(), student.getNationality(), student.getGender());
            }
        }
    }

    //Print the name, nationaility and gender of students who registered for a certain index
    public void printByIndex(Index index){
        ArrayList<Student> students = index.getRegisteredStud();
            for(Student student: students){
                System.out.printf("%s, %s, %s\n", student.getName(), student.getNationality(), student.getGender());
        }
    }
}
