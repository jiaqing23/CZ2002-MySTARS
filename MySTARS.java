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
import java.util.Collections;
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
        //Demonstration prepopulation, comment it out after prepopulation before login into Admin Account again!!
        Admin firstAdmin = new Admin("Mr. Admin", "admin", this);
        this.admins.add(firstAdmin);
        System.out.println("Prepopulating Students, Courses, Indexes, Classes ...");
        Populate.prepopulate(firstAdmin, this.courses);
        System.out.println("Finished prepopulating!");
        this.saveData();
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

    public ArrayList<Student> getStudents(){
        return students;
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
            
            //System.out.println("Data saved!");
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
            System.out.println("Previous data loaded!");
            o.close();
            f.close();
            return loadedData;
        } catch (FileNotFoundException e) {
            System.out.println("Previous data not found");
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
        while(true){
            System.out.println("(1)Admin\t(2)Student");
            System.out.print("Mode: ");
            mode = readInt();
            if (mode == 1 || mode == 2) break;
            else System.out.println("Please enter 1 or 2 to choose the mode!");
        }
        int tries = 3;
        while(tries>=0){
            System.out.print("Username: ");
            this.username = sc.nextLine();
            System.out.print("Password: ");
            String password = new String(System.console().readPassword());
            if(AccountManager.validateAccount(username, password, this.mode == 1))return true;
            else{
                if(tries>=1)System.out.printf("Wrong username or password! You can try for %d more time(s).%n",tries);
                tries-=1;
            }
        }
        return false;
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
            //e.printStackTrace();
            System.out.println("Please enter integer!");
            System.out.print("Please key in again: ");
            num = readInt();
        }
        return num;
    }

    /**
     * Method that prints the Course vacancies.
     * @param courseCode Course code of vacancies to be printed.
     */
    public void printCourseVacancies(String courseCode){
        Course course = null;
        for(Course c: courses){
            if(courseCode.equals(c.getCourseCode()))
                course = c;
        }
        if(course == null){
            System.out.println("The course code doesn't exists!");
            return;
        }
        String alignFormat = "| %-12s | %-10d | %-9d | %-10d |%n";
        System.out.printf("%n%s: %s%n", course.getCourseCode(), course.getCourseName());
        System.out.format("+--------------+------------+-----------+------------+%n");
        System.out.format("|   Index No   | Class Size |  Vacancy  |  Waitlist  |%n");
        System.out.format("+--------------+------------+-----------+------------+%n");
        for (Index i: course.getIndexes()){
            System.out.format(alignFormat, i.getIndexNo(), i.getClassSize(), i.getVacancy(), i.getWaitlistLength());
        }
        System.out.format("+--------------+------------+-----------+------------+%n");
    }

    public void printAllCourses(){
        String alignFormat = "| %-11s | %-43s | %-10s | %-6d |%n";
        System.out.format("+-------------+---------------------------------------------+------------+--------+%n");
        System.out.format("| Course Code |                 Course Name                 |   School   |   AU   |%n");
        System.out.format("+-------------+---------------------------------------------+------------+--------+%n");
        for (Course c: courses){
            System.out.format(alignFormat, c.getCourseCode(), c.getCourseName(), c.getSchool(), c.getNumOfAU());
        }
        System.out.format("+-------------+---------------------------------------------+------------+--------+%n");
    }

    /**
     * Method that prints a Course timetable.
     * @param courseCode Course code of timetable to be printed.
     */
    public void printCourseTimetable(String courseCode){
        Course course = null;
        for(Course c: courses){
            if(courseCode.equals(c.getCourseCode()))
                course = c;
        }
        if(course == null){
            System.out.println("The course code doesn't exists!");
            return;
        }
        String alignFormat = "| %-12s | %-14s | %-10s | %-10s | %-30s | %-13s |%n";
        System.out.printf("%n%s: %s%n", course.getCourseCode(), course.getCourseName());
        System.out.format("+--------------+----------------+------------+------------+--------------------------------+---------------+%n");
        System.out.format("|   Index No   |    Class ID    |    Type    |  Group No  |              Time              |     Venue     |%n");
        System.out.format("+--------------+----------------+------------+------------+--------------------------------+---------------+%n");
        
        for (Index i: course.getIndexes()){
            boolean first = true;
            if(i.getClasses().isEmpty()){
                System.out.format(alignFormat, i.getIndexNo(),"", "", "", "", "");
            }
            for(Class c: i.getClasses()){
                System.out.format(alignFormat, first?i.getIndexNo():"", c.getClassID(), c.getType(), c.getGroup(), c.getTimePeriodString(), c.getVenue());
                first = false;
            }
        }
        System.out.format("+--------------+----------------+------------+------------+--------------------------------+---------------+%n");
    }

    public void printHeader(){
        int random = (int)(Math.random() * 4); 

        switch(random){
            case 0:
                System.out.println(" __    __  __  __       ______  ______  ______  ______  ______    \r\n/\\ \"-./  \\/\\ \\_\\ \\     /\\  ___\\/\\__  _\\/\\  __ \\/\\  == \\/\\  ___\\   \r\n\\ \\ \\-./\\ \\ \\____ \\    \\ \\___  \\/_/\\ \\/\\ \\  __ \\ \\  __<\\ \\___  \\  \r\n \\ \\_\\ \\ \\_\\/\\_____\\    \\/\\_____\\ \\ \\_\\ \\ \\_\\ \\_\\ \\_\\ \\_\\/\\_____\\ \r\n  \\/_/  \\/_/\\/_____/     \\/_____/  \\/_/  \\/_/\\/_/\\/_/ /_/\\/_____/");
                break;
            case 1:
                System.out.println(" __  __    _  _             ___    _____    ___     ___     ___   \r\n|  \\/  |  | || |    o O O  / __|  |_   _|  /   \\   | _ \\   / __|  \r\n| |\\/| |   \\_, |   o       \\__ \\    | |    | - |   |   /   \\__ \\  \r\n|_|__|_|  _|__/   TS__[O]  |___/   _|_|_   |_|_|   |_|_\\   |___/  \r\n_|\"\"\"\"\"|_| \"\"\"\"| {======|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| \r\n\"`-0-0-'\"`-0-0-'./o--000'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'");
                break;
            case 2:
                System.out.println(" \u2588\u2588\u2588\u2584 \u2584\u2588\u2588\u2588\u2593\u2588\u2588   \u2588\u2588\u2593     \u2588\u2588\u2588\u2588\u2588\u2588 \u2584\u2584\u2584\u2588\u2588\u2588\u2588\u2588\u2593 \u2584\u2584\u2584       \u2588\u2588\u2580\u2588\u2588\u2588    \u2588\u2588\u2588\u2588\u2588\u2588 \r\n\u2593\u2588\u2588\u2592\u2580\u2588\u2580 \u2588\u2588\u2592\u2592\u2588\u2588  \u2588\u2588\u2592   \u2592\u2588\u2588    \u2592 \u2593  \u2588\u2588\u2592 \u2593\u2592\u2592\u2588\u2588\u2588\u2588\u2584    \u2593\u2588\u2588 \u2592 \u2588\u2588\u2592\u2592\u2588\u2588    \u2592 \r\n\u2593\u2588\u2588    \u2593\u2588\u2588\u2591 \u2592\u2588\u2588 \u2588\u2588\u2591   \u2591 \u2593\u2588\u2588\u2584   \u2592 \u2593\u2588\u2588\u2591 \u2592\u2591\u2592\u2588\u2588  \u2580\u2588\u2584  \u2593\u2588\u2588 \u2591\u2584\u2588 \u2592\u2591 \u2593\u2588\u2588\u2584   \r\n\u2592\u2588\u2588    \u2592\u2588\u2588  \u2591 \u2590\u2588\u2588\u2593\u2591     \u2592   \u2588\u2588\u2592\u2591 \u2593\u2588\u2588\u2593 \u2591 \u2591\u2588\u2588\u2584\u2584\u2584\u2584\u2588\u2588 \u2592\u2588\u2588\u2580\u2580\u2588\u2584    \u2592   \u2588\u2588\u2592\r\n\u2592\u2588\u2588\u2592   \u2591\u2588\u2588\u2592 \u2591 \u2588\u2588\u2592\u2593\u2591   \u2592\u2588\u2588\u2588\u2588\u2588\u2588\u2592\u2592  \u2592\u2588\u2588\u2592 \u2591  \u2593\u2588   \u2593\u2588\u2588\u2592\u2591\u2588\u2588\u2593 \u2592\u2588\u2588\u2592\u2592\u2588\u2588\u2588\u2588\u2588\u2588\u2592\u2592\r\n\u2591 \u2592\u2591   \u2591  \u2591  \u2588\u2588\u2592\u2592\u2592    \u2592 \u2592\u2593\u2592 \u2592 \u2591  \u2592 \u2591\u2591    \u2592\u2592   \u2593\u2592\u2588\u2591\u2591 \u2592\u2593 \u2591\u2592\u2593\u2591\u2592 \u2592\u2593\u2592 \u2592 \u2591\r\n\u2591  \u2591      \u2591\u2593\u2588\u2588 \u2591\u2592\u2591    \u2591 \u2591\u2592  \u2591 \u2591    \u2591      \u2592   \u2592\u2592 \u2591  \u2591\u2592 \u2591 \u2592\u2591\u2591 \u2591\u2592  \u2591 \u2591\r\n\u2591      \u2591   \u2592 \u2592 \u2591\u2591     \u2591  \u2591  \u2591    \u2591        \u2591   \u2592     \u2591\u2591   \u2591 \u2591  \u2591  \u2591  \r\n       \u2591   \u2591 \u2591              \u2591                 \u2591  \u2591   \u2591           \u2591  \r\n           \u2591 \u2591                                                      ");
                break;
            case 3:
                System.out.println("\u2588\u2588\u2588\u2557   \u2588\u2588\u2588\u2557\u2588\u2588\u2557   \u2588\u2588\u2557    \u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\r\n\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2588\u2588\u2551\u255A\u2588\u2588\u2557 \u2588\u2588\u2554\u255D    \u2588\u2588\u2554\u2550\u2550\u2550\u2550\u255D\u255A\u2550\u2550\u2588\u2588\u2554\u2550\u2550\u255D\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2554\u2550\u2550\u2550\u2550\u255D\r\n\u2588\u2588\u2554\u2588\u2588\u2588\u2588\u2554\u2588\u2588\u2551 \u255A\u2588\u2588\u2588\u2588\u2554\u255D     \u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557   \u2588\u2588\u2551   \u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2551\u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255D\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\r\n\u2588\u2588\u2551\u255A\u2588\u2588\u2554\u255D\u2588\u2588\u2551  \u255A\u2588\u2588\u2554\u255D      \u255A\u2550\u2550\u2550\u2550\u2588\u2588\u2551   \u2588\u2588\u2551   \u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2551\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u255A\u2550\u2550\u2550\u2550\u2588\u2588\u2551\r\n\u2588\u2588\u2551 \u255A\u2550\u255D \u2588\u2588\u2551   \u2588\u2588\u2551       \u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2551   \u2588\u2588\u2551   \u2588\u2588\u2551  \u2588\u2588\u2551\u2588\u2588\u2551  \u2588\u2588\u2551\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2551\r\n\u255A\u2550\u255D     \u255A\u2550\u255D   \u255A\u2550\u255D       \u255A\u2550\u2550\u2550\u2550\u2550\u2550\u255D   \u255A\u2550\u255D   \u255A\u2550\u255D  \u255A\u2550\u255D\u255A\u2550\u255D  \u255A\u2550\u255D\u255A\u2550\u2550\u2550\u2550\u2550\u2550\u255D");
                break;
            default:
                break;
        }
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
        }
        
        // Proceed with the login procedures.
        // If login is unsuccessful, print "Invalid Credential" and exit the program.
        if (!mainApp.login()) {
            System.out.println("Invalid Credential!");
            sc.close();
            return;
        }

        mainApp.printHeader();

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

            while(choice != 12){
                
                // Operations that an admin can perform.
                System.out.println();
                System.out.println("// --------------- Welcome to MySTARS! --------------- //");
                System.out.println("(1) Edit registration period ");
                System.out.println("(2) Check registration period");
                System.out.println("(3) Add student");
                System.out.println("(4) Add course");
                System.out.println("(5) Update course");
                System.out.println("(6) Check all courses");
                System.out.println("(7) Check vacancy of course");
                System.out.println("(8) Check timetable of course");
                System.out.println("(9) Print student list");
                System.out.println("(10) Print student list by course");
                System.out.println("(11) Print student list by index number");
                System.out.println("(12) Quit");
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

                    //CHECK REGISTRATION PERIOD //
                    case 2:
                        System.out.println(mainApp.period.getTimePeriodString());
                        break;

                    // ADD STUDENT //
                    case 3:
                        System.out.print("Please enter student's name: ");
                        String name = sc.nextLine();
                        System.out.print("Please enter student's username: ");
                        String username = sc.nextLine();
                        exist = false;
                        for(Student s: mainApp.students){
                            if(s.getUsername().equals(username)){
                                exist = true;
                                System.out.println("Student with this username already exist!");
                                break;
                            }
                        }
                        if(exist) break;
                        System.out.print("Please enter student's password: ");
                        String password = sc.nextLine();
                        System.out.print("Please enter student's maximum AU: ");
                        int maxAU = readInt();
                        String gender;
                        while(true){
                            System.out.print("Please enter student's gender (Female/Male): ");
                            gender = sc.nextLine();
                            if(gender.equalsIgnoreCase("MALE") || gender.equalsIgnoreCase("FEMALE"))
                                break; 
                            System.out.println("Invalid input! Please enter Female / Male !");
                        }
                        System.out.print("Please enter student's nationality: ");
                        String nationality = sc.nextLine();
                        System.out.print("Please enter student's matricula1tion number: ");
                        String matricNumber = sc.nextLine();
                        exist = false;
                        for(Student s: mainApp.students){
                            if(s.getMatricNumber().equals(matricNumber)){
                                exist = true;
                                System.out.println("Student with this matriculation number already exist!");
                                break;
                            }
                        }
                        if(exist) break;

                        System.out.print("Please enter student's email account: ");
                        String email = sc.nextLine();
                        admin.addStudent(name,username,password,maxAU,gender,nationality,matricNumber, email);

                        admin.printStudents();
                        break;

                    // ADD COURSE //
                    case 4:
                        ArrayList<String> schoolList = new ArrayList<String>();
                        Collections.addAll(schoolList,"NBS, CBE, CEE, SCSE, EEE, MSE, MAE, ADM, SOH, SOSS, WKWSCI, SBS, SPMS, ASE, LKCSOM, NIE, RSIS".split(", "));
                        String school;
                        while(true){
                            System.out.print("School List: NBS, CBE, CEE, SCSE, EEE, MSE, MAE, ADM, SoH, SoSS, WKWSCI, SBS, SPMS, ASE, LKCSoM, NIE, RSIS\nPlease choose school of the course: ");
                            school = sc.nextLine().toUpperCase();
                            if(!schoolList.contains(school))
                                System.out.println("Please choose school from the list shown!");
                            else break;
                        }
                        System.out.print("Please enter the course code: ");
                        String courseCode = sc.nextLine();
                        for(Course c: mainApp.courses){
                            if(c.getCourseCode().equals(courseCode)){
                                System.out.println("This course code already exist!");
                                exist = true;
                            }
                        }
                        if(exist) break;
                        System.out.print("Please enter the course name: ");
                        String courseName = sc.nextLine();
                        System.out.print("Please enter the number of AU: ");
                        int numAU = readInt();
                        admin.addCourse(school,courseCode,courseName,numAU);
                        mainApp.printAllCourses();
                        break;

                    // UPDATE COURSE //
                    case 5:
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
                    
                    // CHECK ALL COURSE //
                    case 6:
                        mainApp.printAllCourses();
                        break;
                    
                    // CHECK VACANCY OF A COURSE // 
                    case 7:
                        System.out.print("Please enter the course code: ");
                        courseCode = sc.nextLine();
                        mainApp.printCourseVacancies(courseCode);
                        break;

                    // CHECK TIMETABLE OF A COURSE //
                    case 8:
                        System.out.print("Please enter the course code: ");
                        courseCode = sc.nextLine();
                        mainApp.printCourseTimetable(courseCode);
                        break;
                    
                    //PRINT STUDENT LIST//
                    case 9:
                        admin.printStudents();
                        break;
                    
                    // PRINT REGISTERED STUDENT LIST BY COURSE //
                    case 10:
                        System.out.print("Please enter the course code: ");
                        courseCode = sc.nextLine();
                        for (Course c: mainApp.courses){
                            if(courseCode.equals(c.getCourseCode())){
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
                    case 11:
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
                    case 12:
                        System.out.println("Program terminating...");
                        break;

                    // WRONG INPUT MESSAGE //
                    default:
                        System.out.println("Wrong Input!!");
                        break;
                }
                
                // Saves the data immediately after an operation.
                //System.out.println("Saving data ...");
                mainApp.saveData();
            }
        }
    

        // ------------ STUDENT MODE ------------ //
        // Note: Student can have his/her full access to the system only within the course registration period.
        if(mainApp.mode == 2 && mainApp.period.validatePeriod()){
            
            while(choice != 9){
                // Operations that a student can perform.
                System.out.println();
                System.out.println("// --------------- Welcome to MySTARS! --------------- //");
                System.out.println("(1) Add Course ");
                System.out.println("(2) Drop Course");
                System.out.println("(3) Check/print courses registered");
                System.out.println("(4) Check all courses");
                System.out.println("(5) Check vacancy of course");
                System.out.println("(6) Check timetable of course");
                System.out.println("(7) Change index number of course");
                System.out.println("(8) Swop index number with another student");
                System.out.println("(9) Exit");
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
                        student.printTimetable();
                        break;
                    
                    //CHECK ALL COURSES//
                    case 4:
                        mainApp.printAllCourses();
                        break;

                    // CHECK COURSE's INDEX VACANCY //
                    case 5:
                        System.out.print("Please enter the course code: ");
                        String courseCode = sc.nextLine();
                        mainApp.printCourseVacancies(courseCode);
                        break;
                    
                    // CHECK TIMETABLE OF A COURSE //
                    case 6:
                        System.out.print("Please enter the course code: ");
                        courseCode = sc.nextLine();
                        mainApp.printCourseTimetable(courseCode);
                        break;
                    
                    // CHANGE INDEX NUMBER OF REGISTERED COURSE //
                    case 7:
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
                    case 8:
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
                        if(!AccountManager.validateAccount(username, password, false)){
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
                    case 9:
                        System.out.println("Program terminating...");
                        break;

                    // WRONG INPUT MESSAGE //
                    default:
                        System.out.println("Wrong Input!!");
                        break;
                }
                
                // Saves the data immediately after an operation.
                //System.out.println("Saving data ...");
                mainApp.saveData();
            }
        }
   
        // ------------ STUDENT MODE ------------ //
        // Note: Student will only have limited access to the system when current time is not within the course registration period.
        if(mainApp.mode == 2 && !mainApp.period.validatePeriod()){

            while(choice != 5){

                // Limited access and operations that a Student can perform.
                System.out.println();
                System.out.println("// --------------- Welcome to MySTARS! --------------- //");
                System.out.println("You are not allowed to register any courses now, the registration period is:");
                System.out.println(mainApp.period.getTimePeriodString() + "\n");

                System.out.println("(1) Check/print registered course");
                System.out.println("(2) Check all courses");
                System.out.println("(3) Check vacancy of course");
                System.out.println("(4) Check timetable of course");
                System.out.println("(5) Exit");
                System.out.print("Your choice: ");
                choice = readInt();
                
                Student student = (Student)temp;

                switch(choice){

                    // PRINT REGISTERED COURSE // 
                    case 1:
                        student.printTimetable();
                        break;

                    //CHECK ALL COURSES//
                    case 2:
                        mainApp.printAllCourses();
                        break;

                    // CHECK COURSE's INDEX VACANCY //
                    case 3:
                        System.out.print("Please enter the course code: ");
                        String courseCode = sc.nextLine();
                        mainApp.printCourseVacancies(courseCode);
                        break;
                    
                    // CHECK TIMETABLE OF A COURSE //
                    case 4:
                        System.out.print("Please enter the course code: ");
                        courseCode = sc.nextLine();
                        mainApp.printCourseTimetable(courseCode);
                        break;

                    // EXIT THE PROGRAM //
                    case 5:
                        System.out.println("Program terminating...");
                        break;

                    // WRONG INPUT MESSAGE //
                    default:
                        System.out.println("Wrong Input!!");
                        break;
                }
                
                // Saves the data immediately after an operation.
                //System.out.println("Saving data ...");
                mainApp.saveData();
            }
        }
        sc.close();
    }
}