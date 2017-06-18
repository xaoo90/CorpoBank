package mailer;

import java.util.Date;
import java.util.Properties;
import javax.faces.context.FacesContext;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author Anek
 */
public class MailSender {
    private final String LOGIN = "wieslawbankowy@gmail.com";
    private final String PASS = "BankCorpo930509";
    private final String SMTPSERVER = "smtp.gmail.com";
    private final String PORT = "587";

    private String to;
    private String message;
    private String subject;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int sendMail()
    {
        try
        {
            Properties props = System.getProperties();
              props.put("mail.transport.protocol", "smtp" );
              props.put("mail.smtp.starttls.enable","true" );
              props.put("mail.smtp.host",SMTPSERVER);
              props.put("mail.smtp.port", PORT);
              props.put("mail.smtp.auth", "true" );
              
              Authenticator auth = new MailSender.SMTPAuthenticator();
              Session session = Session.getInstance(props, auth);
              Message msg = new MimeMessage(session);
              msg.setFrom(new InternetAddress(LOGIN));
              msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
              msg.setSubject(subject);
              msg.setText(message);
              msg.setHeader("MojMail", "CorpoBank" );
              msg.setSentDate(new Date());
              Transport.send(msg);
              FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("text", "Wiadomość została wysłana!");
              return 1;
        }
        catch (Exception ex)
        {
            System.out.println("MailSender print(); catch");
          FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("error", "Błąd wysyłania wiadomości");
          return 0;
        }
        
  }

  
    
    /**
     * Creates a new instance of MailBean
     */
    public MailSender() {
    }
    private class SMTPAuthenticator extends javax.mail.Authenticator {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                String username =  LOGIN; 
                String password = PASS;
                return new PasswordAuthentication(username, password);
            }
      }
}

