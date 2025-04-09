package dao.impl;

import entity.IAdoptable;
import entity.HumanParticipant; 
import util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.AdoptionEventDAO;

public class AdoptionEventDAOImpl implements AdoptionEventDAO {

    @Override
    public List<String> getUpcomingEvents() {
        List<String> events = new ArrayList<>();
        String query = "SELECT EventID, EventName, Location, EventDate FROM adoption_events";

        try (Connection conn = DBConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("EventID");
                String name = rs.getString("EventName");
                String location = rs.getString("Location");
                String date = rs.getString("EventDate");

                events.add(id + " - " + name + " at " + location + " on " + date);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error fetching events: " + e.getMessage());
        }

        return events;
    }

    @Override
    public boolean registerParticipant(IAdoptable participant, int eventId) {
        if (!(participant instanceof HumanParticipant)) {
            System.out.println("❌ Unsupported participant type.");
            return false;
        }

        HumanParticipant hp = (HumanParticipant) participant;

        String query = "INSERT INTO participants (ParticipantName, ParticipantType, EventID) VALUES (?, ?, ?)";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, hp.getName());
            stmt.setString(2, hp.getType());
            stmt.setInt(3, eventId);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error registering participant: " + e.getMessage());
            return false;
        }
    }
}

