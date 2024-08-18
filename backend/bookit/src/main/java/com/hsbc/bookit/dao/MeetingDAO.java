package com.hsbc.bookit.dao;

import com.hsbc.bookit.domain.Meetings;

public interface MeetingDAO {

    void addMeeting(Meetings meeting);
    void removeMeeting(String id);

}
