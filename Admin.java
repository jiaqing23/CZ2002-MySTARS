
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Represents an admin that uses the MySTARS system application.
 * An Admin is an user, therefore it inherits the User class.
 */
public class Admin extends User{

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
     * @param email New Student's email account.
     */
    public void addStudent(String name, String username, String password, int maxAU, String gender, String nationality, String matricNumber, String email){
        if(PasswordManager.addAccount(username, password)){
            Student newStudent = new Student(name, username, maxAU, gender, nationality, matricNumber, email);
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
        for(Course c: mainApp.getCourses()){
            if(c.getCourseCode().equals(courseCode)){
                System.out.println("This course code is already exist!");
                return;
            }
        }

        Course course = new Course(school, courseCode, courseName, numOfAU);
        mainApp.addCourse(course);
        System.out.println("Course added!");
    }

    /**
     * Method that allows Admin to update an existing Course.
     * @param course The exisiting Course object to be updated.
     */
    public void updateCourse(Course course){

        boolean exist = false;
        int choice = 0;
        
        while(choice != 7){
            System.out.println();
            System.out.println("// --------------- Updating course " + course.getCourseCode() + ": " + course.getCourseName() + " --------------- //");
            System.out.println("(1) Update course code");
            System.out.println("(2) Update course name");
            System.out.println("(3) Update school");
            System.out.println("(4) Add index");
            System.out.println("(5) Update index");
            System.out.println("(6) Remove index");
            System.out.println("(7) Back to home page");
            System.out.print("Select Option: ");
            Scanner sc = MySTARS.getScanner();
            choice = MySTARS.readInt();
            String tem;
            
            switch(choice){

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
                    System.out.print("Please enter the index: ");
                    tem = sc.nextLine();
                    System.out.print("Please enter the class size: ");
                    int classSize = MySTARS.readInt();
                    for(Course c: mainApp.getCourses()){
                        for(Index i: c.getIndexes()){
                            if(i.getIndexNo().equals(tem)){
                                exist = true;
                                break;
                            }
                        }
                        if(exist) break;
                    }
                    if(!exist) course.addIndex(new Index(course, tem, classSize)); 
                    break;

                case 5:
                    System.out.print("Please enter the index: ");
                    tem = sc.nextLine();
                    for(Index i: course.getIndexes()){
                        if(i.getIndexNo().equals(tem)){
                            course.updateIndex(i, mainApp);
                            exist = true;
                            break;
                        }
                    }

                    if(!exist) {
                        System.out.println("The index number does not exists!");
                    }
                    break;

                case 6:
                    System.out.print("Please enter the index: ");
                    tem = sc.nextLine();
                    for(Index i: course.getIndexes()){
                        if(i.getIndexNo().equals(tem)){
                            course.dropIndex(i);
                            exist = true;
                            break;
                        }
                    }

                    if(!exist) {
                        System.out.println("The index number does not exists!");
                    }
                    break;

                case 7:
                    System.out.println("Returning to home page...");
                    break;

                default:
                    System.out.println("Wrong Input!!");
                    break;
            }
            System.out.println("Saving data...");
            mainApp.saveData();
        }
    }

    /**
     * Method that allows Admin to check the vacancy of an existing Index of a existing Course.
     * @param index The exisiting Index object to be updated.
     */
    public void checkVacancy(Index index){
        System.out.printf("Index %s have %d slots left.%n", index.getIndexNo(), index.getVacancy());
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
                System.out.printf("%s, %s, %s%n", student.getName(), student.getNationality(), student.getGender());
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
                System.out.printf("%s, %s, %s%n", student.getName(), student.getNationality(), student.getGender());
        }
    }
}
