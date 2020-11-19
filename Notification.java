import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Represents an Email Notification that will be sent after a successful course registration.
 */
public class Notification {

    /**
     * Static method to initiate the process of sending an email notification.
     * @param recepient Email account of a recepient.
     * @param txt Text to include in the email notification.
     */
    public static void sendMail(String recepient, String txt){
        System.out.println("Preparing to send email to " + recepient);
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        String myAccountEmail = "ntumystars@gmail.com";
        String password = "ntu753951";

        Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myAccountEmail, password);
                }
            }
        );

        Message message = prepareMessage(session, myAccountEmail, recepient, txt); 

        try {
           Transport.send(message);
           System.out.println("Email sent successfully to " + recepient + "!");
        } catch (Exception ex) {
           System.out.println("Email failed to send to " + recepient + "!");
           ex.printStackTrace();
        }
    }

    /**
     * Static method to prepare the layout of the email.
     * @param session Current mail session.
     * @param myAccountEmail Host email account address.
     * @param recepient Recepient's email account address.
     * @param txt Email notification content.
     * @return Email message model object.
     */
    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String txt) {
        try {
           Message message = new MimeMessage(session);
           message.setFrom(new InternetAddress(myAccountEmail));
           message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient)); 
           message.setSubject("Course Registration"); 
            
           // set Email content
           message.setText(txt);
           return message;
        } catch (Exception ex) {
           System.out.println("Email failed to send!");
           Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Driver program of Email notification.
     * @param args Unused.
     */
    public static void main(String[] args) {
        String msg = "Name:\t\tWONGJIAWEN\nMatric No.:\tacasdasda\n\nWe are pleased to inform you that you have been allocated the following course in coming semester:\nsadasdvavgzrhs";

        sendMail("wjwen5@gmail.com", msg);
    }
}
