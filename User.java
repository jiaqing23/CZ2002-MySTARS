import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String username;

    //Class Constructor
    public User(String name, String username){
        this.name = name;
        this.username = username;
    }

    //Get method
    public String getUsername(){
        return username;
    }

    public String getName(){
        return name;
    }

}
