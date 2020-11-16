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


public class MySTARS implements Serializable{
    private static String fileName = "mySTARS.txt";
    private int mode;
    private String username;
    private static final Scanner sc = new Scanner(System.in);

    private Period period = new Period();
    private ArrayList<Admin> admins = new ArrayList<Admin>();
    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Course> courses = new ArrayList<Course>();
    private static Student peer = null;

    public MySTARS() {
        // TBD
    }

    public int getMode(){
        return mode;
    }

    public void setMode(int mode){
        this.mode = mode;
    }

    //Add a new student into the system
    public void addStudent(Student student) {
        students.add(student);
    }

    //Add a new course into the system
    public void addCourse(Course course) {
        courses.add(course);
    }

    //Get the Add/Drop period
    public Period getPeriod(){
        return period;
    }

    //Get the list of all courses
    public ArrayList<Course> getCourses(){
        return courses;
    }

    //Save data into file
    public boolean saveData() {
        try {
            FileOutputStream f = new FileOutputStream(new File(fileName));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(this);

            o.close();
            f.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }

        return false;
    }

    //Load data from file
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

    //Login method
    public boolean login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("(1)Admin\t(2)Student");
        System.out.print("Mode: ");
        this.setMode(sc.nextInt());
        System.out.print("Username: ");
        this.username = sc.next();
        System.out.print("Password: ");
        String password = new String(System.console().readPassword());
        //String password = sc.next();
        return PasswordManager.validateAccount(username, password, this.mode == 1);
    }

    public static void main(String[] args) {
        int choice=0;
        String index;
        boolean exist=false;

        MySTARS mainApp = loadData();
        if (mainApp == null) {
            mainApp = new MySTARS();
            mainApp.admins.add(new Admin("admin111", "admin", mainApp));
        }
        
        if (!mainApp.login()) {
            System.out.println("Invalid Credential!");
            sc.close();
            return;
        }

        User temp = null;

        if(mainApp.mode == 2){
            for (Student s : mainApp.students) {
                if(s.getUsername().equals(mainApp.username)){
                    temp = s;
                    break;
                }
            }
        }
        else {
            for (Admin a : mainApp.admins) {
                if(a.getUsername().equals(mainApp.username)){
                    temp = a;
                    break;
                }
            }
        }
        
        //mainApp.mode=1 mean admin mainApp.mode
        //Admin can login in any period
        if(mainApp.mode == 1) {
            while(choice != 8){
                //Operations that an admin can performs
                System.out.println("*************Welcome to MySTARS!*************");
                System.out.println("(1) Edit registeration period ");
                System.out.println("(2) Add student");
                System.out.println("(3) Add Course");
                System.out.println("(4) Update Course");
                System.out.println("(5) Check available slot for an index number");
                System.out.println("(6) Print student list by index number");
                System.out.println("(7) Print student list by course");
                System.out.println("(8) Quit");
                System.out.print("Your choice: ");

                choice = Integer.parseInt(sc.nextLine());
                Admin admin = (Admin)temp;

                switch(choice){
                    case 1:
                        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
                        System.out.println("Please enter the start date (dd-mm-yyyy):");
                        String startDate=sc.nextLine();
                        System.out.println("Please enter the end date (dd-mm-yyyy):");
                        String endDate=sc.nextLine();
                        try{
                            admin.editPeriod(dateFormat.parse(startDate),dateFormat.parse(endDate));
                        }catch(ParseException e){
                            System.out.println("Please use the correct format (dd-mm-yyyy) !");
                        }
                        break;
                    case 2:
                        System.out.println("Please enter student's name:");
                        String name = sc.nextLine();
                        System.out.println("Please enter student's username:");
                        String username = sc.nextLine();
                        System.out.println("Please enter student's password:");
                        String password = sc.nextLine();
                        System.out.println("Please enter student's maximum AU:");
                        int maxAU = sc.nextInt();
                        String dummy = sc.nextLine();
                        System.out.println("Please enter student's gender:");
                        String gender = sc.nextLine();
                        System.out.println("Please enter student's nationality:");
                        String nationality = sc.nextLine();
                        System.out.println("Please enter student's Matriculation Number:");
                        String matricNumber = sc.nextLine();
                        admin.addStudent(name,username,password,maxAU,gender,nationality,matricNumber);
                        break;
                    case 3:
                        System.out.println("Please enter school of the course:");
                        String school = sc.nextLine();
                        System.out.println("Please enter the course code:");
                        String courseCode = sc.nextLine();
                        System.out.println("Please enter the course name:");
                        String courseName = sc.nextLine();
                        System.out.println("Please enter the number of AU:");
                        int numAU = sc.nextInt();
                        dummy = sc.nextLine();
                        admin.addCourse(school,courseCode,courseName,numAU);
                        break;
                    case 4:
                        System.out.println("Please enter the course code:");
                        courseCode = sc.next();
                        for (Course c: mainApp.courses){
                            if(courseCode == c.getCourseCode()){
                                    admin.updateCourse(c);
                                    exist = true;
                                    break;
                                    
                            }
                        }
                        if(!exist){
                            System.out.println("The course name does not exist!");
                        }
                        break;

                    case 5:
                        System.out.println("Please enter the index:");
                        index = sc.next();
                        exist = false;
                        dummy = sc.nextLine();
                        for (Course c: mainApp.courses){
                            if(!exist){
                                for(Index i : c.getIndexes()){
                                if(index == i.getIndexNo()){
                                    admin.checkVacancy(i);
                                    exist = true;
                                    break;
                                }
                                    
                            }}
                        }
                        if(!exist){
                            System.out.println("The index number does not exist!");
                        }
                        break;
                    case 6:
                        System.out.println("Please enter the course name:");
                        courseName = sc.nextLine();
                        exist = false;
                        for (Course c: mainApp.courses){
                            if(courseName == c.getCourseName()){
                                    admin.printByCourse(c);
                                    exist = true;
                                    break;
                                    
                            }
                        }
                        if(!exist){
                            System.out.println("The course name does not exist!");
                        }
                        break;
                    case 7:
                        System.out.println("Please enter the index number:");
                        index = sc.next();
                        exist = false;
                        for (Course c: mainApp.courses){
                            if(!exist){
                                for(Index i : c.getIndexes()){
                                if(index == i.getIndexNo()){
                                    admin.printByIndex(i);
                                    exist = true;
                                    break;
                                }
                                    
                            }}
                        }
                        if(!exist){
                            System.out.println("The index number does not exist!");
                        }
                        break;
                    case 8:
                        System.out.println("Program terminating...");
                        break;
                    default:
                        System.out.println("Wrong Input!!");
                        break;
                }
                System.out.println(mainApp.saveData()?"Successfully Saved!":"Failed");
            }
        }
    

        //mainApp.mode=2 mean student mainApp.mode
        //Need to make sure that the login period is around the period opened for add/drop
        if(mainApp.mode == 2 && mainApp.period.validatePeriod()){
            while(choice != 7){
                //Operations that a student can perform
                System.out.println("*************Welcome to MySTARS!*************");
                System.out.println("(1) Add Course ");
                System.out.println("(2) Drop Course");
                System.out.println("(3) Print Course Registered");
                System.out.println("(4) Check Course Vacancy");
                System.out.println("(5) Change Index Number of Course Registered");
                System.out.println("(6) Swap Index with Another Student");
                System.out.println("(7) Quit");
                choice = sc.nextInt();
                
                Student student = (Student)temp;
                Index currIndex = null;
                switch(choice){
                    case 1:
                        System.out.println("Please enter the index number:");
                        index = sc.next();
                        exist = false;
                        for (Course c: mainApp.courses){
                            if(!exist){
                                for(Index i : c.getIndexes()){
                                if(index == i.getIndexNo()){
                                    student.addIndex(i);
                                    exist = true;
                                    break;
                                }
                                    
                            }}
                        }
                        if(!exist){
                            System.out.println("The index number does not exist!");
                        }
                        break;
                    case 2:
                        System.out.println("Please enter the index number:");
                        index = sc.next();
                        exist = false;
                        for (Index i: student.getRegistered()){
                            if(index == i.getIndexNo()){
                                student.dropIndex(i);
                                exist = true;
                                break;
                            }
                        }
                        for (Index i: student.getWaitlist()){
                            if(index == i.getIndexNo()){
                                student.dropIndex(i);
                                exist = true;
                                break;
                            }
                        }
                        if(!exist){
                            System.out.println("You haven't registered for this index!");
                        }
                        break;
                    case 3:
                        student.printIndex();
                        break;
                    case 4:
                        System.out.println("Please enter the index number:");
                        index = sc.next();
                        exist = false;
                        for (Course c: mainApp.courses){
                            if(!exist){
                                for(Index i : c.getIndexes()){
                                if(index == i.getIndexNo()){
                                    student.checkVacancy(i);
                                    exist = true;
                                    break;
                                }
                                    
                            }}
                        }
                        if(!exist){
                            System.out.println("The index number does not exist!");
                        }
                        break;
                    case 5:
                        System.out.println("Please enter the current index number:");
                        index = sc.next();
                        exist = false;
                        for (Index i: student.getRegistered()){
                            if(index == i.getIndexNo()){
                                currIndex = i;
                                exist = true;
                                break;
                            }   
                        }
                        for (Index i: student.getWaitlist()){
                            if(index == i.getIndexNo()){
                                currIndex = i;
                                exist = true;
                                break;
                            }   
                        }
                        if(!exist){
                            System.out.printf("You don't have the index number %s!\n",index);
                            break;
                        }
                        System.out.println("Please enter the new index number:");
                        index = sc.next();
                        exist = false;
                        for (Index i: currIndex.getCourse().getIndexes()){
                            if(index == i.getIndexNo()){
                                student.changeIndex(currIndex,i);
                                exist = true;
                                break;
                            }   
                        }
                        for (Index i: currIndex.getCourse().getIndexes()){
                            if(index == i.getIndexNo()){
                                student.changeIndex(currIndex,i);
                                exist = true;
                                break;
                            }   
                        }
                        if(!exist){
                            System.out.printf("The index number %s does not exist in the same course!\n", index);
                            break;
                        }
                        break;
                    case 6:
                        System.out.println("Please enter peer's username:");
                        String me = sc.nextLine();
                        exist = false;
                        for (Student s: mainApp.students){
                            if(me == s.getUsername()){
                                peer = s;
                                exist = true;
                                break;
                            }
                        }
                        if(!exist){
                            System.out.printf("The username does not exist!");
                            break;
                        }

                        System.out.print("Please enter peer's Password: ");
                        String password = new String(System.console().readPassword());
                        if(!PasswordManager.validateAccount(me, password, mainApp.mode == 1)){
                            System.out.println("Wrong password/username!");
                            break;
                        }

                        System.out.println("Please enter the current index number:");
                        index = sc.next();
                        for (Course c: mainApp.courses){
                                for(Index i : c.getIndexes()){
                                if(index == i.getIndexNo()){
                                    currIndex = i;
                                    break;
                                }    
                            }
                        }
                        System.out.println("Please enter the new index number:");
                        index = sc.next();
                        for (Course c: mainApp.courses){
                            for(Index i : c.getIndexes()){
                                if(index == i.getIndexNo()){
                                    student.swapIndex(currIndex, i, peer);
                                    break;
                                }    
                            }
                        }
                        break;
                    case 7:
                        System.out.println("Program terminating...");
                        break;
                    default:
                        System.out.println("Wrong Input!!");
                        break;
                }
                System.out.println(mainApp.saveData()?"Successfully Saved!":"Failed");
            }
        }
   
        //If the period is not opened for add/drop
        //The student can only check the courses registered and vacancy of a course
        if(mainApp.mode == 2 && !mainApp.period.validatePeriod()){
            while(choice != 3){
                System.out.println("*************Welcome to MySTARS!*************");
                System.out.println("(1) Print Course Registered");
                System.out.println("(2) Check Course Vacancy");
                System.out.println("(3) Quit");
                choice = sc.nextInt();
                
                Student student = (Student)temp;

                switch(choice){
                    case 1:
                        student.printIndex();
                        break;
                    case 2:
                        System.out.println("Please enter the index number:");
                        index = sc.next();
                        exist = false;
                        for (Course c: mainApp.courses){
                            if(!exist){
                                for(Index i : c.getIndexes()){
                                if(index == i.getIndexNo()){
                                    student.checkVacancy(i);
                                    exist = true;
                                    break;
                                }
                                    
                            }}
                        }
                        if(!exist){
                            System.out.println("The index number does not exist!");
                        }
                        break;
                    case 3:
                        System.out.println("Program terminating...");
                        break;
                    default:
                        System.out.println("Wrong Input!!");
                        break;
                }
                System.out.println(mainApp.saveData()?"Successfully Saved!":"Failed");
            }
        }
        sc.close();
    }
}

