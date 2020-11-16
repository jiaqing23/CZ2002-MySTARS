//Base Class for Student and Admin
public class User {
    private String name;//e.g. Tan Ah Beng
    // private String userID;
    private String username;//e.g. BENG010

    //Class Constructor
    public User(String name, String username){
        this.name = name;
        //this.userID = userID;
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
