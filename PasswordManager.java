import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class PasswordManager {
    private static String fileName = "account.txt";

    public static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String getHash(String originalString) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean addAccount(String username, String password) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] pieces = data.split("\\s+");

                if(pieces[0].equals(username))
                    return false;
            }

            FileWriter fw = new FileWriter(fileName, true);
            fw.write(username + " " + getHash(password) + " 0\n");
            fw.close();
            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }

        return false;
    }

    public static boolean validateAccount(String username, String password, boolean isStaff) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] pieces = data.split("\\s+");

                if(pieces[0].equals(username) && pieces[1].equals(getHash(password)) && (pieces[2].equals("0") ^ isStaff))
                    return true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
       
        return false;
    }

    public static void main(String[] args) {
        System.out.println(validateAccount("jiaqing23","23032000",false)); //True
        System.out.println(validateAccount("jiaqing23","23032000",true)); //False
        System.out.println(validateAccount("jiaqing23","23032001",false)); //False
        System.out.println(validateAccount("jiaqing24","23032001",false)); //False
    }
}
