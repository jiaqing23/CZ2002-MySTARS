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
    private int isStaff;
    private String username;
    private String password;

    // private Period period = new Period();
    // private ArrayList<Admin> admins = new ArrayList<Admin>();
    // private ArrayList<Student> students = new ArrayList<Student>();
    // private ArrayList<Course> courses = new ArrayList<Course>();

    // public void addStudent(Student student){
    //     students.add(student);
    // }

    // public void addCourse(Course course){
    //     courses.add(course);
    // }

    public boolean saveData(){
        try{
            FileOutputStream f = new FileOutputStream(new File(fileName));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(this);

            o.close();
            f.close();
            return true;
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } 
        
        return false;
    }

    public static MySTARS loadData(){
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

    public MySTARS(){
        //TBD
    }

    public boolean login(){
        Scanner sc = new Scanner(System.in);
        System.out.println("(1)Admin\t(2)Student");
        System.out.print("Mode: ");
        this.choice = sc.nextInt(); 
        System.out.print("Username: ");
        this.username = sc.next();
        System.out.print("Password: ");
        this.password = new String(System.console().readPassword());
        return PasswordManager.validateAccount(username, password, choice==1);
    }

    public static void main(String[] args){
        MySTARS mainApp = loadData();
        if(mainApp == null){
            mainApp = new MySTARS();
        }

        System.out.println(mainApp.login());
        if(mainApp.login()){
            //TBD
        }
        else{
            System.out.println("Invalid Credential!");
            return;
        }


    }
}
