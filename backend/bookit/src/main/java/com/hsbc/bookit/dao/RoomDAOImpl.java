package com.hsbc.bookit.dao;

import com.hsbc.bookit.domain.Rooms;
import com.hsbc.bookit.util.ConManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO{

    // Assume we have a utility class for DB connection
    private Connection conn;

    public RoomDAOImpl() {
        conn = ConManager.getConnection(); // Utility to handle DB connection
    }

    @Override
    public void addRoom(Rooms room) {
        String query = "INSERT INTO Rooms (name, seating_capacity) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, room.getName());
            stmt.setInt(2, room.getSeatingCapacity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRoom(int roomId, String roomName, int seatingCapacity) {
        String query = "UPDATE Rooms SET name = ?, seating_capacity = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, roomName);
            stmt.setInt(2, seatingCapacity);
            stmt.setInt(3, roomId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRoom(int roomId) {
        String query = "DELETE FROM Rooms WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, roomId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Rooms> getAllRooms() {
        String query = "SELECT * FROM Rooms";
        List<Rooms> roomsList = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Rooms room = new Rooms(rs.getString("name"), rs.getInt("seating_capacity"));
                room.setId(rs.getInt("id"));
                roomsList.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomsList;
    }
}
