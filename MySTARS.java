import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class MySTARS implements Serializable{
    private static String fileName = "mySTARS.txt";
    private static int mode;
    private String username;

    private Period period = new Period();
    private ArrayList<Admin> admins = new ArrayList<Admin>();
    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Course> courses = new ArrayList<Course>();

    public MySTARS() {
        // TBD
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public Period getPeriod(){
        return period;
    }

    public ArrayList<Course> getCourses(){
        return courses;
    }

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

    public boolean login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("(1)Admin\t(2)Student");
        System.out.print("Mode: ");
        mode = sc.nextInt();
        System.out.print("Username: ");
        this.username = sc.next();
        System.out.print("Password: ");
        String password = new String(System.console().readPassword());
        return PasswordManager.validateAccount(username, password, mode == 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        String index;
        boolean exist;

        MySTARS mainApp = loadData();
        if (mainApp == null) {
            mainApp = new MySTARS();
        }
        
        if (!mainApp.login()) {
            System.out.println("Invalid Credential!");
            return;
        }

        User temp;
        
        if(mode == 2){
            for (Student s : mainApp.students) {
                if(s.getUsername() == mainApp.username){
                    temp = s;
                    break;
                }
            }
        }
        else {
            for (Admin a : mainApp.admins) {
                if(a.getUsername() == mainApp.username){
                    temp = a;
                    break;
                }
            }
        }

        if(mode == 2 && mainApp.period.validatePeriod()){
            while(choice != 7){
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
                        for (Course c: mainApp.courses){
                            if(!exist){
                                for(Index i : c.getIndexes()){
                                if(index == i.getIndexNo()){
                                    student.dropIndex(i);
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
                        Index currIndex;
                        System.out.println("Please enter the current index number:");
                        index = sc.next();
                        exist = false;
                        for (Course c: mainApp.courses){
                            if(!exist){
                                for(Index i : c.getIndexes()){
                                if(index == i.getIndexNo()){
                                    currIndex = i;
                                    exist = true;
                                    break;
                                }
                                    
                            }}
                        }
                        if(!exist){
                            System.out.printf("The index number %d does not exist!\n",index);
                            break;
                        }
                        System.out.println("Please enter the new index number:");
                        index = sc.next();
                        exist = false;
                        for (Course c: mainApp.courses){
                            if(!exist){
                                for(Index i : c.getIndexes()){
                                if(index == i.getIndexNo()){
                                    student.changeIndex(currIndex,i);
                                    exist = true;
                                    break;
                                }
                                    
                            }}
                        }
                        if(!exist){
                            System.out.printf("The index number %d does not exist!\n",index);
                            break;
                        }
                        break;
                    case 6:
                    Student peer;
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
                        System.out.printf("The index number %d does not exist!\n",index);
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
                            student.swapIndex(currIndex,i,student,peer);
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

        if(mode == 2 && !mainApp.period.validatePeriod()){
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

        if(mode == 1) {
            while(choice != 8){
                System.out.println("*************Welcome to MySTARS!*************");
                System.out.println("(1) Edit registeration period ");
                System.out.println("(2) Add student");
                System.out.println("(3) Add Course");
                System.out.println("(4) Update Course");
                System.out.println("(5) Check available slot for an index number");
                System.out.println("(6) Print student list by index number");
                System.out.println("(7) Print student list by course");
                System.out.println("(8) Quit");
                choice = sc.nextInt();
                
                Admin admin = (Admin)temp;

                switch(choice){
                    case 1:
                        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
                        System.out.println("Please enter the start date (dd-mm-yyyy):");
                        String startDate=sc.next();
                        System.out.println("Please enter the end date (dd-mm-yyyy):");
                        String endDate=sc.next();
                        try{
                            admin.editPeriod(dateFormat.parse(startDate),dateFormat.parse(endDate));
                        }catch(DateTimeParseException e){
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
                        admin.addStudent(name,username,password,maxAU,gender,nationality);
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
                        courseCode = sc.nextLine();
                        admin.updateCourse(courseCode);
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


    }

    public Period getPeriod(){
        return this.period;
    }

    public ArrayList<Course> getCourses(){
        return this.courses;
    }
}

