package entity;

import exception.InsufficientFundsException;
import java.time.LocalDate;

public class CashDonation extends Donation {
    private LocalDate donationDate;

    public CashDonation(String donorName, double amount, LocalDate donationDate) throws InsufficientFundsException {
        super(donorName, amount);
        if (amount < 50) {
            throw new InsufficientFundsException("❌ Minimum donation amount is ₹50.");
        }
        this.donationDate = donationDate;
    }

    public LocalDate getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(LocalDate donationDate) {
        this.donationDate = donationDate;
    }

    @Override
    public void recordDonation() {
        System.out.println("💸 Cash Donation Recorded:");
        System.out.println("Donor Name: " + donorName);
        System.out.println("Amount: ₹" + amount);
        System.out.println("Date: " + donationDate);
    }

    @Override
    public String toString() {
        return "CashDonation [Donor: " + donorName + ", Amount: ₹" + amount + ", Date: " + donationDate + "]";
    }
}
