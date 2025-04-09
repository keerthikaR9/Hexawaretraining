package util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LoggerUtil {

    private static final String DONATION_LOG_FILE = "donations_log.txt";
    private static final String ADOPTION_LOG_FILE = "adoptions_log.txt";

    public static void logDonation(String donorName, double amount) {
        try (FileWriter writer = new FileWriter(DONATION_LOG_FILE, true)) {
            writer.write("Donor: " + donorName + ", Amount: ₹" + amount + ", Date: " + LocalDateTime.now() + "\n");
        } catch (IOException e) {
            System.out.println(" Failed to log donation: " + e.getMessage());
        }
    }
    public static void logAdoption(String petName, String petType, String breed) {
        try (FileWriter writer = new FileWriter(ADOPTION_LOG_FILE, true)) {
            writer.write("Pet Adopted: " + petType + " | Name: " + petName + " | Breed: " + breed + " | Date: " + LocalDateTime.now() + "\n");
        } catch (IOException e) {
            System.out.println("❌ Failed to log adoption: " + e.getMessage());
        }
    }

}
