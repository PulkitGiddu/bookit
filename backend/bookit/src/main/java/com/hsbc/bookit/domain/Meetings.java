package com.hsbc.bookit.domain;

import java.util.Date;

public class Meetings {
    private int id;
    private int roomId;
    private int managerId;
    private Date startTime;
    private Date endTime;
    private String status;

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

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
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
