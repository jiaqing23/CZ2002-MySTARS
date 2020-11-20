import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the Course Registration main application class.
 */
public class MySTARS implements Serializable{

    private static final Scanner sc = new Scanner(System.in);

    /**
     * File name that acts as a database that stores the application data.
     * Default file name is "mySTARS.txt"
     */
    private static String fileName = "mySTARS.txt";

    /**
     * Application mode. Admin == 1, Student == 2.
     */
    private int mode;

    /**
     * Account username for login procedure.
     */
    private String username;

    /**
     * Course Registration main application class is associated with a Period.
     */
    private Period period = new Period();

    /**
     * List of Admin objects that will be read in from database.
     */
    private ArrayList<Admin> admins = new ArrayList<Admin>();

    /**
     * List of Student objects that will be read in from database.
     */
    private ArrayList<Student> students = new ArrayList<Student>();

    /**
     * List of Course objects that will be read in from database.
     */
    private ArrayList<Course> courses = new ArrayList<Course>();

    /**
     * Peer Student object that is used for swapping Index.
     */
    private static Student peer = null;

    /**
     * Creates a MySTARS application object with the given parameters.
     */
    public MySTARS() {
        // TBD
    }

    /**
     * Method that gets the mode of the main application.
     * @return Mode of the main application.
     */
    public int getMode(){
        return mode;
    }

    public static Scanner getScanner(){
        return sc;
    }

    /**
     * Method that changes the mode of the main application.
     * @param mode New mode of the calling MySTARS object.
     */
    public void setMode(int mode){
        this.mode = mode;
    }

    /**
     * Method that adds a new Student to the main application.
     * @param student New Student to be added to the calling MySTARS object.
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Method that adds a new Course to the main application.
     * @param course New Course to be added to the calling MySTARS object.
     */
    public void addCourse(Course course) {
        courses.add(course);
    }

    /**
     * Method that gets the course registration Period of the main application.
     * @return Course registration Period.
     */
    public Period getPeriod(){
        return period;
    }

    /**
     * Method that gets all the courses of the main application.
     * @return Courses currently in the calling MySTARS object.
     */
    public ArrayList<Course> getCourses(){
        return courses;
    }

    /**
     * Method that saves all the main application's data into a binary text file.
     * @return True when it is successfully saved, else return false.
     */
    public boolean saveData() {
        try {
            FileOutputStream f = new FileOutputStream(new File(fileName));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(this);

            o.close();
            f.close();
            
            System.out.println("Data saved!");
            System.out.println();
            return true;

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }

        return false;
    }

    /**
     * Method that loads all the main applications's data from a binary text file.
     * @return MySTARS object that has loaded with the main application's data.
     */
    public static MySTARS loadData() {
        try {
            FileInputStream f = new FileInputStream(new File(fileName));
            ObjectInputStream o = new ObjectInputStream(f);

            MySTARS loadedData = (MySTARS) o.readObject();

            System.out.println(loadedData.toString());

            o.close();
            f.close();

            return loadedData;

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method that process the account login procedure of a User.
     * @return True when the login is successful, else return false.
     */
    public boolean login() {
        System.out.println("(1)Admin\t(2)Student");
        System.out.print("Mode: ");
        int mode = readInt();
        if (mode<=0 || mode>2) return false;
        this.setMode(mode);
        System.out.print("Username: ");
        this.username = sc.nextLine();
        System.out.print("Password: ");
        String password = new String(System.console().readPassword());
        return PasswordManager.validateAccount(username, password, this.mode == 1);
    }

    /**
     * Method that reads a String into an Integer.
     * @return Return the converted String.
     */
    public static int readInt(){
        int num = 0;
        try {
            num = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return num;
    }

    /**
     * Method that reads a String into a Double.
     * @return Return the converted String
     */
    public static double readDouble() {
        double num = 0;

        try {
            num = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return num;
    }

    /**
     * Main method (driver program).
     * @param args Unused.
     */
    public static void main(String[] args) {

        int choice=0;
        String index;
        boolean exist=false;

        // Load the data to the MySTARS main application object.
        MySTARS mainApp = loadData();

        if (mainApp == null) {
            mainApp = new MySTARS();
            mainApp.admins.add(new Admin("admin111", "admin", mainApp));
        }
        
        // Proceed with the login procedures.
        // If login is unsuccessful, print "Invalid Credential" and exit the program.
        if (!mainApp.login()) {
            System.out.println("Invalid Credential!");
            sc.close();
            return;
        }

        for(Student s: mainApp.students){
            System.out.println(s.getUsername());
        }

        // Create a User object to proceed with the main application's functionalities.
        User temp = null;

        // If mode equals to 2 (STUDENT), check whether the Student account's username exist in the system.
        // If exist, assign that Student with the specific username as the User.
        if(mainApp.mode == 2){
            for (Student s : mainApp.students) {
                if(s.getUsername().equals(mainApp.username)){
                    temp = s;
                    break;
                }
            }
        }
        // Else if mode equals to 1 (ADMIN), check whether the Admin account's username exist in the system.
        // If exist, assign that Admin with the specific username as the User. 
        else if(mainApp.mode == 1){
            for (Admin a : mainApp.admins) {
                if(a.getUsername().equals(mainApp.username)){
                    temp = a;
                    break;
                }
            }
        }

        // ------------ ADMIN MODE ------------ //
        // Note: Admin can have his/her full access to the system regardless of the course registration period.
        if(mainApp.mode == 1) {

            Admin admin = (Admin)temp;

            // Demonstration prepopulation, comment it out after prepopulation before login into Admin Account again!!
            // System.out.println("Prepopulating Students, Courses, Indexes, Classes ...");
            // Populate.prepopulate(admin, mainApp.courses);
            // System.out.println("Finished prepopulating!");

            while(choice != 8){
                
                // Operations that an admin can perform.
                System.out.println();
                System.out.println("// --------------- Welcome to MySTARS! --------------- //");
                System.out.println("(1) Edit registeration period ");
                System.out.println("(2) Add student");
                System.out.println("(3) Add course");
                System.out.println("(4) Update course");
                System.out.println("(5) Check vacancy of an index number");
                System.out.println("(6) Print student list by course");
                System.out.println("(7) Print student list by index number");
                System.out.println("(8) Quit");
                System.out.print("Your choice: ");
                choice = readInt();
                
                exist = false;

                switch(choice){

                    // EDIT REGISTRATION PERIDOD //
                    case 1:
                        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
                        System.out.print("Please enter the start date (dd-mm-yyyy): ");
                        String startDate=sc.nextLine();
                        System.out.print("Please enter the end date (dd-mm-yyyy): ");
                        String endDate=sc.nextLine();
                        try{
                            admin.editPeriod(dateFormat.parse(startDate),dateFormat.parse(endDate));
                        } catch(ParseException e){
                            System.out.println("Please use the correct format (dd-mm-yyyy)!");
                        }
                        break;

                    // ADD STUDENT //
                    case 2:
                        System.out.print("Please enter student's name: ");
                        String name = sc.nextLine();
                        System.out.print("Please enter student's username: ");
                        String username = sc.nextLine();
                        System.out.print("Please enter student's password: ");
                        String password = sc.nextLine();
                        System.out.print("Please enter student's maximum AU: ");
                        int maxAU = readInt();
                        String gender;
                        while(true){
                            System.out.print("Please enter student's gender (Female/Male): ");
                            gender = sc.nextLine();
                            if(gender.toUpperCase().equals("MALE")||gender.toUpperCase().equals("FEMALE"))
                            break;
                            else 
                            System.out.print("Invalid input! Please enter Female / Male !");
                        }
                        System.out.print("Please enter student's nationality: ");
                        String nationality = sc.nextLine();
                        System.out.print("Please enter student's matriculation number: ");
                        String matricNumber = sc.nextLine();
                        System.out.print("Please enter student's email account: ");
                        String email = sc.nextLine();
                        admin.addStudent(name,username,password,maxAU,gender,nationality,matricNumber, email);
                        break;

                    // ADD COURSE //
                    case 3:
                        System.out.println("Please choose school of the course: ");
                        String school = sc.nextLine();
                        System.out.print("Please enter the course code: ");
                        String courseCode = sc.nextLine();
                        System.out.print("Please enter the course name: ");
                        String courseName = sc.nextLine();
                        System.out.print("Please enter the number of AU: ");
                        int numAU = readInt();
                        admin.addCourse(school,courseCode,courseName,numAU);
                        break;

                    // UPDATE COURSE //
                    case 4:
                        System.out.print("Please enter the course code: ");
                        courseCode = sc.nextLine();
                        for(Course c: mainApp.courses){
                            if(courseCode.equals(c.getCourseCode())){
                                    admin.updateCourse(c);
                                    exist = true;
                                    break;
                            }
                        }
                        if(!exist){
                            System.out.println("The course code doesn't exists!");
                        }
                        break;
                    
                    // CHECK VACANCY OF AN INDEX // 
                    case 5:
                        System.out.print("Please enter the index: ");
                        index = sc.nextLine();
                        for (Course c: mainApp.courses){
                            if(!exist){
                                for(Index i : c.getIndexes()){
                                    if(index.equals(i.getIndexNo())) {
                                        admin.checkVacancy(i);
                                        exist = true;
                                        break;
                                    }
                                }
                            }
                        }
                        if(!exist){
                            System.out.println("The index number does not exists!");
                        }
                        break;

                    // PRINT REGISTERED STUDENT LIST BY COURSE //
                    case 6:
                        System.out.print("Please enter the course code: ");
                        courseName = sc.nextLine();
                        for (Course c: mainApp.courses){
                            if(courseName.equals(c.getCourseCode())){
                                    admin.printByCourse(c);
                                    exist = true;
                                    break;
                                    
                            }
                        }
                        if(!exist){
                            System.out.println("The course name does not exists!");
                        }
                        break;

                    // PRINT REGISTERED STUDENT LIST BY INDEX //
                    case 7:
                        System.out.print("Please enter the index number: ");
                        index = sc.nextLine();
                        for (Course c: mainApp.courses){
                            if(!exist){
                                for(Index i : c.getIndexes()){
                                    if(index.equals(i.getIndexNo())){
                                        admin.printByIndex(i);
                                        exist = true;
                                        break;
                                    }
                                }
                            }
                        }
                        if(!exist){
                            System.out.println("The index number does not exists!");
                        }
                        break;
                    
                    // EXIT THE PROGRAM //
                    case 8:
                        System.out.println("Program terminating...");
                        break;

                    // WRONG INPUT MESSAGE //
                    default:
                        System.out.println("Wrong Input!!");
                        break;
                }
                
                // Saves the data immediately after an operation.
                //System.out.println(mainApp.saveData()?"Successfully Saved!":"Failed");
                System.out.println("Saving data ...");
                mainApp.saveData();
            }
        }
    

        // ------------ STUDENT MODE ------------ //
        // Note: Student can have his/her full access to the system only within the course registration period.
        if(mainApp.mode == 2 && mainApp.period.validatePeriod()){
            
            while(choice != 7){

                // Operations that a student can perform.
                System.out.println();
                System.out.println("// --------------- Welcome to MySTARS! --------------- //");
                System.out.println("(1) Add Course ");
                System.out.println("(2) Drop Course");
                System.out.println("(3) Check/Print Courses Registered");
                System.out.println("(4) Check Vacancies Available");
                System.out.println("(5) Change Index Number of Course");
                System.out.println("(6) Swop Index Number with Another Student");
                System.out.println("(7) Exit");
                System.out.print("Your choice: ");
                choice = readInt();
                
                Student student = (Student)temp;
                Index currIndex = null;
                exist = false;
                
                switch(choice){
                    // REGISTER COURSE //
                    case 1:
                        System.out.print("Please enter the index number: ");
                        index = sc.nextLine();

                        for (Course c: mainApp.courses){
                            for(Index i : c.getIndexes()){
                                if(index.equals(i.getIndexNo())){
                                    student.addIndex(i);
                                    exist = true;
                                    break;
                                }
                            }
                            if(exist) break; 
                        }
                        if(!exist){
                            System.out.println("The index number does not exists!");
                        }
                        break;

                    // DROP COURSE //
                    case 2:
                        System.out.print("Please enter the index number: ");
                        index = sc.nextLine();
                        for(Course c: mainApp.courses){
                            for(Index i: c.getIndexes()){
                                if(index.equals(i.getIndexNo())){
                                    exist = true;
                                    student.dropIndex(i);
                                    break;
                                }
                            }
                            if(exist) break;
                        }
                        if(!exist){
                            System.out.println("This index number does not exists!");
                        }
                        break;

                    // PRINT REGISTERED COURSE //
                    case 3:
                        student.printIndex();
                        break;

                    // CHECK COURSE's INDEX VACANCY //
                    case 4:
                        System.out.print("Please enter the index number: ");
                        index = sc.nextLine();
                        for (Course c: mainApp.courses){
                            for(Index i : c.getIndexes()){
                                if(index.equals(i.getIndexNo())){
                                    int vacancy = student.checkVacancy(i);
                                    System.out.println("Vacancies: " + vacancy);
                                    exist = true;
                                    break;
                                }
                            }
                            if(exist) break;
                        }
                        if(!exist){
                            System.out.println("The index number does not exists!");
                        }
                        break;

                    // CHANGE INDEX NUMBER OF REGISTERED COURSE //
                    case 5:
                        System.out.print("Please enter the current index number: ");
                        index = sc.nextLine();
                        for (Course c: mainApp.courses){
                            for(Index i : c.getIndexes()){
                                if(index.equals(i.getIndexNo())){
                                    exist = true;
                                    currIndex = i;
                                    break;
                                }
                            }
                            if(exist) break;
                        }
                        if(!exist){
                            System.out.println("The index number does not exists!");
                            break;
                        }
                        System.out.print("Please enter the new index number: ");
                        index = sc.nextLine();
                        for (Index i: currIndex.getCourse().getIndexes()){
                            if(index.equals(i.getIndexNo())){
                                student.changeIndex(currIndex,i);
                                exist = true;
                                break;
                            }   
                        }
                        if(!exist){
                            System.out.printf("The index number %s does not exists in the same course!%n", index);
                            break;
                        }
                        break;

                    // SWAP INDEX NUMBER WITH ANOTHER STUDENT //
                    case 6:
                        System.out.print("Please enter peer's username: ");
                        String username = sc.nextLine();
                        for (Student s: mainApp.students){
                            if(username.equals(s.getUsername())){
                                peer = s;
                                exist = true;
                                break;
                            }
                        }
                        if(!exist){
                            System.out.println("The username does not exists!");
                            break;
                        }

                        System.out.print("Please enter peer's Password: ");
                        String password = new String(System.console().readPassword());
                        if(!PasswordManager.validateAccount(username, password, false)){
                            System.out.println("Wrong password/username!");
                            break;
                        }

                        System.out.print("Please enter the your index number: ");
                        index = sc.nextLine();
                        for (Course c: mainApp.courses){
                                for(Index i : c.getIndexes()){
                                if(index.equals(i.getIndexNo())){
                                    currIndex = i;
                                    break;
                                }    
                            }
                        }
                        System.out.print("Please enter the your peer's index number: ");
                        index = sc.nextLine();
                        for (Course c: mainApp.courses){
                            for(Index i : c.getIndexes()){
                                if(index.equals(i.getIndexNo())){
                                    student.swapIndex(currIndex, i, peer);
                                    break;
                                }    
                            }
                        }
                        break;

                    // EXIT THE PROGRAM //
                    case 7:
                        System.out.println("Program terminating...");
                        break;

                    // WRONG INPUT MESSAGE //
                    default:
                        System.out.println("Wrong Input!!");
                        break;
                }
                // Saves the data immediately after an operation.
                //System.out.println(mainApp.saveData()?"Successfully Saved!":"Failed");
                System.out.println("Saving data ...");
                mainApp.saveData();
            }
        }
   
        // ------------ STUDENT MODE ------------ //
        // Note: Student will only have limited access to the system when current time is not within the course registration period.
        if(mainApp.mode == 2 && !mainApp.period.validatePeriod()){

            while(choice != 3){

                // Limited access and operations that a Student can perform.
                System.out.println();
                System.out.println("// --------------- Welcome to MySTARS! --------------- //");
                System.out.println("(1) Check/Print Registered Course");
                System.out.println("(2) Check Vacancies Available");
                System.out.println("(3) Exit");
                System.out.print("Your choice: ");
                choice = readInt();
                
                Student student = (Student)temp;
                exist = false;

                switch(choice){

                    // PRINT REGISTERED COURSE // 
                    case 1:
                        student.printIndex();
                        break;

                    // CHECK COURSE's INDEX VACANCY //
                    case 2:
                        System.out.print("Please enter the index number: ");
                        index = sc.nextLine();
                        for (Course c: mainApp.courses){
                            if(!exist){
                                for(Index i : c.getIndexes()){
                                if(index.equals(i.getIndexNo())){
                                    student.checkVacancy(i);
                                    exist = true;
                                    break;
                                }
                                    
                            }}
                        }
                        if(!exist){
                            System.out.println("The index number does not exists!");
                        }
                        break;

                    // EXIT THE PROGRAM //
                    case 3:
                        System.out.println("Program terminating...");
                        break;

                    // WRONG INPUT MESSAGE //
                    default:
                        System.out.println("Wrong Input!!");
                        break;
                }
                // Saves the data immediately after an operation.
                //System.out.println(mainApp.saveData()?"Successfully Saved!":"Failed");
                System.out.println("Saving data ...");
                mainApp.saveData();
            }
        }
        sc.close();
    }
}

