package utility;

import java.util.Properties;
import javax.mail.*;
import javax.mail.search.FlagTerm;

public class OTPFetcher {

    public static String getOtpFromEmail(String host, String storeType, String user, String password) {
        String otp = null;

        try {
            Properties properties = new Properties();
            properties.put("mail.imap.host", host);
            properties.put("mail.imap.port", "993");
            properties.put("mail.imap.starttls.enable", "true");

            Session emailSession = Session.getDefaultInstance(properties);
            Store store = emailSession.getStore("imap");
            store.connect(host, user, password);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            // Get unread messages
            Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

            for (int i = messages.length - 1; i >= 0; i--) {
                String content = messages[i].getContent().toString();

                // Extract OTP using regex
                java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\b\\d{6}\\b");  // 6-digit code
                java.util.regex.Matcher m = p.matcher(content);
                if (m.find()) {
                    otp = m.group();
                    break;
                }
            }

            inbox.close(false);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return otp;
    }

    public static void main(String[] args) {
        String host = "imap.gmail.com";
        String mailStoreType = "imap";
        String username = "your.email@gmail.com";
        String password = "your_app_password"; // Use App Password if 2FA is enabled

        String otp = getOtpFromEmail(host, mailStoreType, username, password);
        System.out.println("OTP fetched: " + otp);
    }
}
