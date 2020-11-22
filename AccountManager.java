import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Represents a Account Manager that creates Admin/Student's accounts and validates the login process of the application.
 * The account password is hashed by one of the method of the Account Manager class.
 */
public class AccountManager implements Serializable{

    /**
     * File name that stores the Admin/Student's account information.
     * Default file name is "account.txt"
     * <p>
     * <b>Note:</b> Account password is hashed.
     */
    private static String fileName = "account.txt";

    /**
     * Static method that converts byte arrays to hexadecimals.
     * @param hash Byte arrays to be converted.
     * @return Hexadecimal strings after the conversion.
     */
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

    /**
     * Static method that hashs a String. (Password) for our case.
     * @param originalString Password of an Admin/Student's account.
     * @return Hashed password.
     */
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

    /**
     * Static method that adds an account and store it in an binary text file.
     * @param username Username of the account to be added.
     * @param password Hashed password of the account to be added.
     * @return True if the new account is successfully added. False when the username to be added has already exist in the database.
     */
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

    //Method to check whether the password entered for a username is correct or not
    /**
     * Static method that validates the account login process of an User(Admin/Student).
     * @param username Username of the account.
     * @param password Password of the account.
     * @param isStaff True if Admin, false if Student.
     * @return True when login is successful, otherwise false.
     */
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
}
