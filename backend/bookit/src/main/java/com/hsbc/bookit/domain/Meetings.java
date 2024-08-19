package com.hsbc.bookit.domain;

import java.util.Date;

public class Meetings {
    private int id;
    private int roomId;
    private String managerId;
    private Date startTime;
    private Date endTime;
    private String status;

    public Meetings(int id, int roomId, String managerId, Date startTime, Date endTime, String status) {
        this.id = id;
        this.roomId = roomId;
        this.managerId = managerId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Meeting{id=" + id + ", roomId=" + roomId + ", managerId=" + managerId + ", startTime=" + startTime + ", endTime=" + endTime + ", status='" + status + '\'' + '}';
    }
}
