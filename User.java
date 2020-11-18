import java.io.Serializable;

/**
 * Represent uesrs (Admin/Student) that uses the system application.
 * User is a superclass that has derived class of Admin or Student.
 */
public class User implements Serializable {
    /**
     * Name of the user.
     * Name should include first name and last name.
     */
    private String name;

    /**
     * Account username of the user.
     * Username should be unique.
     */
    private String username;

    /**
     * Creates an user with the given parameters.
     * @param name Name of this User.
     * @param username Username of this User. Username should be unique.
     */
    public User(String name, String username){
        this.name = name;
        this.username = username;
    }

    /**
     * Method that gets the account username of an User.
     * @return The account username of the calling User object.
     */
    public String getUsername(){
        return username;
    }

    /**
     * Method that gets the full name of an User.
     * @return The full name of the calling User object.
     */
    public String getName(){
        return name;
    }

}
