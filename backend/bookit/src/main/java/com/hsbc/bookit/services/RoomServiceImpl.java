package com.hsbc.bookit.services;

import com.hsbc.bookit.dao.RoomDAO;
import com.hsbc.bookit.dao.RoomDAOImpl;
import com.hsbc.bookit.domain.Rooms;
import com.hsbc.bookit.domain.Users;

public class RoomServiceImpl implements RoomService {

    private final RoomDAO roomDAO = new RoomDAOImpl();
    private final LoginServiceImpl loginService = new LoginServiceImpl();
    private Users authenticatedUser;
    protected boolean adminAccess() {
        if (authenticatedUser == null || !authenticatedUser.getRole().equalsIgnoreCase("admin")){
            return false;
        }
        return true;
    }


    public void addRoom(int id,String roomName, int seatingCapacity) {
        adminAccess();
        Rooms room = new Rooms(id,roomName, seatingCapacity);
        roomDAO.addRoom(room);
        System.out.println("Room added: " + roomName);
    }

    public void updateRoom(int roomId, String roomName, int seatingCapacity) {
        adminAccess();
        roomDAO.updateRoom(roomId, roomName, seatingCapacity);
        System.out.println("Room updated: " + roomName);
    }

    public void deleteRoom(int roomId) {
        adminAccess();
        roomDAO.deleteRoom(roomId);
        System.out.println("Room deleted: " + roomId);
    }

    public void getAllRooms() {
        roomDAO.getAllRooms().forEach(System.out::println);
    }
}
