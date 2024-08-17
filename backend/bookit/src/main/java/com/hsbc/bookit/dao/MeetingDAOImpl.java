package com.hsbc.bookit.dao;

import com.hsbc.bookit.domain.Meetings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MeetingDAOImpl implements MeetingDAO{
    private Connection conn;

    public MeetingDAOImpl() {
        conn = DatabaseConnection.getConnection(); // Utility to handle DB connection
    }

    @Override
    public void bookMeeting(Meetings meeting) {
        String query = "INSERT INTO Meetings (room_id, manager_id, start_time, end_time, status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, meeting.getRoomId());
            stmt.setInt(2, meeting.getManagerId());
            stmt.setTimestamp(3, new java.sql.Timestamp(meeting.getStartTime().getTime()));
            stmt.setTimestamp(4, new java.sql.Timestamp(meeting.getEndTime().getTime()));
            stmt.setString(5, meeting.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
