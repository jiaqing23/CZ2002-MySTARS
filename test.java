import java.util.ArrayList;

public class test {
    

    public static void main(String[] args) {
        ArrayList<User> tem = new ArrayList<User>();
        tem.add(new User("xxx", "yyyy"));
        tem.add(new User("11", "22"));
        tem.add(new User("33", "44"));
        tem.add(new User("55", "66"));

        System.out.println(tem.size());

        tem.remove(0);
        System.out.println(tem.size());
    }
}
