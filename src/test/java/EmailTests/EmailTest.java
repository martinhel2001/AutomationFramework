package EmailTests;

import Connectors.EmailsConnector;
import enums.EmailServer;
import enums.EmailUser;
import org.testng.annotations.Test;

public class EmailTest {
    EmailsConnector emailsConnector = new EmailsConnector();

    @Test (description = "display all emails on yeti-admin@automationcampus.com.ar")
    public void yetiTest() {
        emailsConnector.check(EmailServer.AUTOMATIONCAMPUS.getHost(),"pop3", EmailUser.YETI_admin.getUserName(), EmailUser.YETI_admin.getPassword());
    }

    @Test (description = "display all emails on akaunting-admin@automationcampus.com.ar")
    public void akauntingTest() {
        emailsConnector.check(EmailServer.AUTOMATIONCAMPUS.getHost(),"pop3", EmailUser.AKAUNTING_admin.getUserName(), EmailUser.AKAUNTING_admin.getPassword());
    }

    @Test (description = "display all emails on sentrifugo-admin@automationcampus.com.ar")
    public void sentrifugoTest() {
        emailsConnector.check(EmailServer.AUTOMATIONCAMPUS.getHost(),"pop3", EmailUser.SENTRIFUGO_admin.getUserName(), EmailUser.SENTRIFUGO_admin.getPassword());
    }
}
