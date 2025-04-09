package dao.impl;

import dao.PetDAO;
import entity.Pet;
import util.DBConnectionUtil;

import java.sql.*;
import java.util.*;

public class PetDAOImpl implements PetDAO {

    @Override
    public List<Pet> getAvailablePets() {
        List<Pet> pets = new ArrayList<>();
        String query = "SELECT PetID, Name, Age, Breed, Type FROM Pets WHERE AvailableForAdoption = 1";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pet pet = new Pet(
                    rs.getInt("PetID"),
                    rs.getString("Name"),
                    rs.getInt("Age"),
                    rs.getString("Breed"),
                    rs.getString("Type")
                );
                pets.add(pet);
            }

        } catch (SQLException e) {
            System.out.println("❌ Failed to retrieve pets: " + e.getMessage());
        }

        return pets;
    }
}
