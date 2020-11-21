import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Represents an Email that implements the Notification interface, that is to be sent to Student who successfully registered a Course.
 */
public class Email implements RegisterNotification {

    /**
     * Method that sends an email notification to Student that successfully registered an Index.
     */
    @Override
    public void sendRegisterNotification(Student student, Index index) {
        String recepient = student.getEmail();


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

        Message message = prepareRegisterNotificationMessage(
                                session, myAccountEmail, recepient, student.getName(), student.getMatricNumber(), 
                                index.getCourse().getCourseCode(), index.getCourse().getCourseName(), index.getIndexNo());

        try {
           Transport.send(message);
           System.out.println("Email sent successfully to " + recepient + "!");
        } catch (Exception ex) {
           System.out.println("Email failed to send to " + recepient + "!");
           ex.printStackTrace();
        }

    }

    /**
     * Method that prepares the layout of the email.
     * @param session Current mail session.
     * @param myAccountEmail Host(System) email account address.
     * @param recepient Recepient's(Student) email account address.
     * @param name Recepient's(Student) name.
     * @param matricNo Recepient's(Student) matriculation number.
     * @param courseCode Succesfully registered course code.
     * @param courseName Succesfully registered course name.
     * @param indexNo Successfully registered index number.
     * @return Email Message model object.
     */
    private Message prepareRegisterNotificationMessage(Session session, String myAccountEmail, String recepient,  String name, String matricNo, String courseCode, String courseName, String indexNo) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient)); 
            message.setSubject("Course Registration"); 

                
            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border-collapse: collapse; border: 1px solid #cccccc;\"> <tr> <td align=\"center\" bgcolor=\"#181d62\" style=\"padding: 0 0 0 0;\"> <img src=\"cid:image\" height=\"230\" style=\"display: block;\"/> </td></tr><tr> <td bgcolor=\"#ffffff\" style=\"padding: 40px 30px 40px 30px;\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;\"> <tr> <td style=\"color: #153643; font-family: Arial, sans-serif;\"> <h1 style=\"font-size: 24px; margin: 0;\">Your registered course has been confirmed!</h1> </td></tr><tr> <td style=\"color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 24px; padding: 20px 0 30px 0;\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\"> <tr> <td> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"left\" width=\"600\"> <colgroup> <col span=\"1\" style=\"width: 25%;\"> <col span=\"1\" style=\"width: 75%;\"> </colgroup> <tr> <td>Name:</td><td>"+name+"</td></tr><tr> <td>Matric No:</td><td>"+matricNo+"</td></tr></table> </td></tr><tr height=\"20px\";colspan=1><td> </td></tr><tr> <td> <b> We are pleased to inform you that you have been allocated the following course in coming semester:</b> </td></tr><tr height=\"10px\";colspan=1><td></td></tr><tr> <td> <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border-collapse: separate; border: 1px solid #000000;\"> <tr style=\"border-collapse: separate; border: 1px solid #000000;\"> <td align=\"center\" style=\"border-collapse: separate; border: 1px solid #000000;\"><b>Course Code</b></td><td align=\"center\" style=\"border-collapse: separate; border: 1px solid #000000;\"><b>Course Name</b></td><td align=\"center\" style=\"border-collapse: separate; border: 1px solid #000000;\"><b>Index</b></td></tr><tr style=\"border-collapse: separate; border: 1px solid #000000;\"> <td style=\"border-collapse: separate; border: 1px solid #000000;\">"+courseCode+"</td><td style=\"border-collapse: separate; border: 1px solid #000000;\">"+courseName+"</td><td style=\"border-collapse: separate; border: 1px solid #000000;\">"+indexNo+"</td></tr></table> </td></tr></table> </td></tr></table> </td></tr><tr> <td bgcolor=\"#D7143F\" style=\"padding: 30px 30px;\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;\"> <tr> <td style=\"color: #ffffff; font-family: Arial, sans-serif; font-size: 14px;\"> <p style=\"margin: 0;\">Email generated automatically by system<br/> Digital signature does not required</p></td></tr></table> </td></tr></table>";
            messageBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource(
                "./logo.png");

            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image>");

            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            return message;
        } catch (Exception ex) {
            System.out.println("Email failed to send!");
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }   
}
