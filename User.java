//Base Class for Student and Admin
public class User {
    private String name;
    // private String userID;
    private String username;

    public User(String name, String username){
        this.name = name;
        //this.userID = userID;
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public String getName(){
        return name;
    }

}
