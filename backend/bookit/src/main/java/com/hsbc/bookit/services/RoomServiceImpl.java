package com.hsbc.bookit.services;

import com.hsbc.bookit.dao.RoomDAO;
import com.hsbc.bookit.dao.RoomDAOImpl;
import com.hsbc.bookit.domain.Rooms;
import com.hsbc.bookit.domain.Users;
import com.hsbc.bookit.exceptions.AccessDeniedException;

public class RoomServiceImpl implements RoomService {


    private RoomDAO roomDAO = new RoomDAOImpl();
    private LoginServiceImpl loginService = new LoginServiceImpl();



    public Users authenticatedUser;
    public RoomServiceImpl(){

    }
    public RoomServiceImpl(RoomDAO roomDAO, LoginServiceImpl loginService) {

        this.roomDAO = roomDAO;
        this.loginService = loginService;
    }

    protected boolean adminAccess() {
        if (authenticatedUser == null || !authenticatedUser.getRole().equalsIgnoreCase("admin")){
            return false;
        }
        return true;
    }


    public void addRoom(int id,String roomName, int seatingCapacity) {
        if (!adminAccess()) {
            throw new AccessDeniedException("Access denied");
        }
        Rooms room = new Rooms(id,roomName, seatingCapacity);
        roomDAO.addRoom(room);
        System.out.println("Room added: " + roomName);
    }

    public void updateRoom(int roomId, String roomName, int seatingCapacity) {
        if (!adminAccess()) {
            throw new AccessDeniedException("Access denied");
        }
        roomDAO.updateRoom(roomId, roomName, seatingCapacity);
        System.out.println("Room updated: " + roomName);
    }

    public void deleteRoom(int roomId) {
        if (!adminAccess()) {
            throw new AccessDeniedException("Access denied");
        }
        roomDAO.deleteRoom(roomId);
        System.out.println("Room deleted: " + roomId);
    }

    public void getAllRooms() {
        roomDAO.getAllRooms().forEach(System.out::println);
    }
}
