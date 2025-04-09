// dao/impl/DonationDAOImpl.java
package dao.impl;

import entity.CashDonation;
import entity.ItemDonation;
import util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DonationDAOImpl {

    public void recordCashDonation(CashDonation donation) {
        String query = "INSERT INTO Donations (DonarName, DonationType, DonationAmount, DonationItem) VALUES (?, ?, ?, NULL)";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, donation.getDonorName());
            stmt.setString(2, "Cash");
            stmt.setDouble(3, donation.getAmount());

            stmt.executeUpdate();
            System.out.println("✅ Cash donation recorded successfully!");

        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    public void recordItemDonation(ItemDonation donation) {
        String query = "INSERT INTO Donations (DonarName, DonationType, DonationAmount, DonationItem) VALUES (?, ?, NULL, ?)";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, donation.getDonorName());
            stmt.setString(2, "Item");
            stmt.setString(3, ((ItemDonation) donation).getItemType());

            stmt.executeUpdate();
            System.out.println("✅ Item donation recorded successfully!");

        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }
}
