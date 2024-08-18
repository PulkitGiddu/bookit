package com.hsbc.bookit.dao;

import com.hsbc.bookit.domain.Meetings;
import com.hsbc.bookit.exceptions.MeetingAlreadyExistsException;
import com.hsbc.bookit.util.ConManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MeetingDAOImpl implements MeetingDAO{
    private Connection conn;

    public MeetingDAOImpl() {
        conn = ConManager.getConnection(); // Utility to handle DB connection
    }

    @Override
    public void addMeeting(Meetings meeting) {
        String query = "INSERT INTO Meetings (id,room_id, manager_id, start_time, end_time, status) VALUES (?,?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, meeting.getId());
            stmt.setInt(2, meeting.getRoomId());
            stmt.setString(3, meeting.getManagerId());
            stmt.setTimestamp(4, new java.sql.Timestamp(meeting.getStartTime().getTime()));
            stmt.setTimestamp(5, new java.sql.Timestamp(meeting.getEndTime().getTime()));
            stmt.setString(6, meeting.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new MeetingAlreadyExistsException("A Meeting with this id already exists!");
        }
    }

    @Override
    public void removeMeeting(String id) {
        String query = "DELETE FROM Meetings WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, Integer.parseInt(id)); // Set the ID of the meeting to be removed
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Meeting removed successfully.");
            } else {
                System.out.println("No meeting found with the provided ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
