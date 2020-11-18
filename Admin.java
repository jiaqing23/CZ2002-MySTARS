import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Represents an admin that uses the MySTARS system application.
 * An Admin is an user, therefore it inherits the User class.
 */
public class Admin extends User implements Serializable{

    /**
     * Admin class object is associated with the MySTARS main application class.
     */
    MySTARS mainApp;

    /**
     * Creates a new Admin with the given name, username and MySTARS class object.
     * The name should include both first and last name.
     * @param name This Admin's full name.
     * @param username This Admin account's username.
     * @param mainApp This Admin's MySTARS class object.
     */
    public Admin(String name, String username, MySTARS mainApp){
        super(name, username);
        this.mainApp = mainApp;
    }
    
    /**
     * Method that allows Admin to update the registration period with the given start and end date.
     * @param start New starting date of the registration period.
     * @param end New ending date of the registration period.
     */
    public void editPeriod(Date start, Date end){
        Period period = mainApp.getPeriod();
        period.setPeriod(start, end);
    }

    /**
     * Method that allows Admin to add and create an account for a Student with the given parameters.
     * @param name New Student's full name.
     * @param username New Student account's username.
     * @param password New Student account's password.
     * @param maxAU New Student's maximum number of AU.
     * @param gender New Student's gender.
     * @param nationality New Student's nationality.
     * @param matricNumber New Student's matriculation number.
     */
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

    /**
     * Method that allows Admin to add an new Course with the given parameters.
     * @param school The school that provides the new Course. E.g. SCSE, MAE, etc.
     * @param courseCode The code of the new Course. E.g. CZ2001, CZ2002, etc.
     * @param courseName The name of the new Course. E.g. Algorithms
     * @param numOfAU The number of academic units of the new Course
     */
    public void addCourse(String school, String courseCode, String courseName, int numOfAU){
        Course course = new Course(school, courseCode, courseName, numOfAU);
        mainApp.addCourse(course);
    }

    /**
     * Method that allows Admin to update an existing Course.
     * @param course The exisiting Course object to be updated.
     */
    public void updateCourse(Course course){
        System.out.println("Updating course " + course.getCourseCode());
        System.out.println("(1) Update Course Code");
        System.out.println("(2) Update Course Name");
        System.out.println("(3) Update School");
        System.out.println("(4) Add Index");
        System.out.println("(5) Update Index");
        System.out.println("(6) Remove Index");
        System.out.println("Select Option: ");
        Scanner sc = new Scanner(System.in);
        int option = MySTARS.readInt();
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
                tem = sc.nextLine();
                System.out.print("IndexNo to be classSize: ");
                int classSize = MySTARS.readInt();
                course.addIndex(new Index(course, tem, classSize)); 
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

    /**
     * Method that allows Admin to check the vacancy of an existing Index of a existing Course.
     * @param index The exisiting Index object to be updated.
     */
    public void checkVacancy(Index index){
        System.out.printf("Index %s have $d slots left.\n", index.getIndexNo(), index.getVacancy());
    }

    /**
     * Method that allows Admin to print all the registered Students of an existing Course. 
     * @param course The existing Course object with the registered Students to be printed. 
     */
    public void printByCourse(Course course){
        ArrayList<Index> indexes = course.getIndexes();
        for(Index index: indexes){
            ArrayList<Student> students = index.getReg();
            for(Student student: students){
                System.out.printf("%s, %s, %s\n", student.getName(), student.getNationality(), student.getGender());
            }
        }
    }

    /**
     * Method that allows Admin to print all the registered Students of an existing Index object of an existing Course.
     * @param index The existing Index object with the registered Students to be printed.
     */
    public void printByIndex(Index index){
        ArrayList<Student> students = index.getReg();
            for(Student student: students){
                System.out.printf("%s, %s, %s\n", student.getName(), student.getNationality(), student.getGender());
        }
    }
}
