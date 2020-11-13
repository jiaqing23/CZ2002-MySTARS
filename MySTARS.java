import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class MySTARS implements Serializable{
    private static String fileName = "mySTARS.txt";
    private static int mode;
    private String username;

    private Period period = new Period();
    private ArrayList<Admin> admins = new ArrayList<Admin>();
    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Course> courses = new ArrayList<Course>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addCourse(Course course) {
        courses.add(course);
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

    public MySTARS() {
        // TBD
    }

    public boolean login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("(1)Admin\t(2)Student");
        System.out.print("Mode: ");
        this.mode = sc.nextInt();
        System.out.print("Username: ");
        this.username = sc.next();
        System.out.print("Password: ");
        String password = new String(System.console().readPassword());
        return PasswordManager.validateAccount(username, password, mode == 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        MySTARS mainApp = loadData();
        if (mainApp == null) {
            mainApp = new MySTARS();
        }
        
        if (!mainApp.login()) {
            System.out.println("Invalid Credential!");
            return;
        }

        // Find user in arraylist
        // Check date!!!!!!!!!!!!!!!
        User temp;
        
        if(mode == 1){
            for (Student s : mainApp.students) {
                if(s.getUsername() == mainApp.username){
                    temp = s;
                    break;
                }
        }}
        else {
            for (Admin a : mainApp.admins) {
                if(a.getUsername() == mainApp.username){
                    temp = a;
                    break;
                }
        }}

        if(mode == 2 && mainApp.period.validatePeriod()){
            while(choice != 8){
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
                        student.addIndex();
                        break;
                    case 2:
                        student.dropIndex();
                        break;
                    case 3:
                        student.printIndex();
                        break;
                    case 4:
                        student.checkVacancy();
                        break;
                    case 5:
                        student.changeIndex();
                        break;
                    case 6:
                        student.swapIndex();
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

        };
        if(mode == 1) cases = 3;

        // if (mode == 2) {
        //     for (Student s : mainApp.students) {
        //         tem = s;
        //         if(s.username = mainApp.username){
        //             if
        //             while(choice != 8){
        //                 System.out.println("*************Welcome to MySTARS!*************");
        //                 System.out.println("(1) Add Course ");
        //                 System.out.println("(2) Drop Course");
        //                 System.out.println("(3) Print Course Registered");
        //                 System.out.println("(4) Check Course Vacancy");
        //                 System.out.println("(5) Change Index Number of Course Registered");
        //                 System.out.println("(6) Swap Index with Another Student");
        //                 System.out.println("(7) Save Changes");
        //                 System.out.println("(8) Quit");
        //                 choice = sc.nextInt();
                        

        //                 switch(choice){
        //                     case 1:
        //                         if(mainApp.period.validatePeriod()){
        //                             boolean output = s.addIndex();
                                    
        //                         }
        //                         else {
        //                             System.out.println("You are not allowed to register course for now!");
        //                             mainApp.period.printPeriod();
        //                     }
        //                     //TBD
        //                     break;
        //                     case 2:
        //                     //TBD
        //                     break;
        //                     case 3:
        //                     //TBD
        //                     break;
        //                     case 4:
        //                     //TBD
        //                     break;
        //                     case 5:
        //                     //TBD
        //                     break;
        //                     case 6:
        //                     //TBD
        //                     break;
        //                     case 7:
        //                     if(saveData())
        //                         System.out.println("Successfully Saved.");
        //                     else System.out.println("Saving Failed.");
        //                     break;
        //                     case 8:
                            
        //                 }
        //             }
                   

        //         }
                    
        //     }
        // }
//save data before logout
        



    }
}
