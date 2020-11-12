import java.util.ArrayList;

public class MySTARS {
    private Period period;
    private ArrayList<Admin> admins = new ArrayList<Admin>();
    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Course> courses = new ArrayList<Course>();

    public addStudent(Student student){
        students.add(student);
    }

    public addCourse(Course course){
        courses.add(course);
    }

    public MySTARS(){
        //TBD: Load all data
    }

    public static void main(String[] args){
        //TBD: Ask for username, admin/student, password; validate
        
    }
}
