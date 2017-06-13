/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Scheduler;

/**
 *
 * @author Dilusha
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
* This class facilitates of sending email to the user management system,Ex:forgot password
* @author Dilusha Weeraddana
* @version 2.0
*/
 
@Service
public class EmailSender {
    
 
   /**
   * This method configures the email,handles end to end communication between sender and recipients.
   * @param host name of the host
   * @param user user name of the sender's email account.
   * @param password password of the sender's password account
   * @param port port
   * @param toList recipients user names
   * @param subject subject of the email body

   * @return Nothing
   */
public void sendMailWithAuth(String host, String user, String password, String port, List<String> toList, String subject) throws Exception {

    
Properties props = System.getProperties();

    props.put("mail.smtp.user",user); 
    props.put("mail.smtp.password", password);
    props.put("mail.smtp.host", host); 
    props.put("mail.smtp.port", port); 
    props.put("mail.smtp.auth", "true"); 
    props.put("mail.smtp.starttls.enable","true"); 
    props.put("mail.smtp.EnableSSL.enable","true");

    Session session = Session.getInstance(props, null);
    

    MimeMessage message = new MimeMessage(session);
    message.setFrom(new InternetAddress(user));

    // To get the array of addresses
    for (String to: toList) {
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    }

    message.setSubject(subject);
    message.setText("Please Note that you have exceeded 80% the allocated used minutes as at");

    Transport transport = session.getTransport("smtp");
    try {
        transport.connect(host, user, password);
        transport.sendMessage(message, message.getAllRecipients());
    } finally {
        transport.close();
    }
        
}
}
