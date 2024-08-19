package com.hsbc.bookit.dao;

import com.hsbc.bookit.domain.Amenities;
import com.hsbc.bookit.util.ConManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AmenityDAOImpl {
    private Connection conn;

    public AmenityDAOImpl() {
        conn = ConManager.getConnection(); // Utility to handle DB connection
    }


    public void addAmenity(Amenities amenity) {
        String query = "INSERT INTO Amenities (name, cost) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, amenity.getName());
            stmt.setInt(2, amenity.getCost());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAmenity(int amenityId, String name, int cost) {
        String query = "UPDATE Amenities SET name = ?, cost = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setInt(2, cost);
            stmt.setInt(3, amenityId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteAmenity(int amenityId) {
        String query = "DELETE FROM Amenities WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, amenityId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Amenities> getAllAmenities() {
        String query = "SELECT * FROM Amenities";
        List<Amenities> amenitiesList = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Amenities amenity = new Amenities(rs.getString("name"), rs.getInt("cost"));
                amenity.setId(rs.getInt("id"));
                amenitiesList.add(amenity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return amenitiesList;
    }
}