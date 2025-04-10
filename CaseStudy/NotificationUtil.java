package util;

public class NotificationUtil {
	public static void sendNotification(String recipient, String subject, String message) {
        // Simulate sending an email/SMS (you can replace this with JavaMail or Twilio later)
        System.out.println("\n===== Notification =====");
        System.out.println("To      : " + recipient);
        System.out.println("Subject : " + subject);
        System.out.println("Message : " + message);
        System.out.println("========================\n");
    }

}
