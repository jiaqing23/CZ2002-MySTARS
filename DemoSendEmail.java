import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class DemoSendEmail {

      public static void sendMail(String recepient, String txt) throws Exception {
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
      });

      Message message = prepareMessage(session, myAccountEmail, recepient, txt); // prepare email

      try {
         Transport.send(message); // send email
         System.out.println("Email sent successfully to " + recepient + "!");
      } catch (Exception ex) {
         System.out.println("Email failed to send to " + recepient + "!");
         ex.printStackTrace();
      }
      }

      private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String txt) {
      try {
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress(myAccountEmail)); // set sender email
         message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient)); // set recipient email
         message.setSubject("APU Exam Results"); // set subject

         message.setText(txt); // set email content
         return message;
      } catch (Exception ex) {
         System.out.println("Email failed to send!");
         Logger.getLogger(DemoSendEmail.class.getName()).log(Level.SEVERE, null, ex);
      }
      return null;
      }

      public static void main(String[] args) {
      try {
         sendMail("tan23qing@gmail.com", "YAY");
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      }
}