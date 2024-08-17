package com.hsbc.bookit.services;

import com.hsbc.bookit.dao.RoomDAO;
import com.hsbc.bookit.dao.RoomDAOImpl;
import com.hsbc.bookit.domain.Rooms;
import com.hsbc.bookit.domain.Users;
import com.hsbc.bookit.exceptions.AccessDeniedException;

public class RoomService {

    private final RoomDAO roomDAO = new RoomDAOImpl();
    private final LoginService loginService = new LoginService();
    private Users authenticatedUser;

    public RoomService(String username, String password) {
        authenticatedUser = loginService.login(username, password);
        if (authenticatedUser == null || !"manager".equalsIgnoreCase(authenticatedUser.getRole())) {
            throw new AccessDeniedException("Only managers can perform this action!");
        }
    }

    public void addRoom(String roomName, int seatingCapacity) {
        Rooms room = new Rooms(roomName, seatingCapacity);
        roomDAO.addRoom(room);
        System.out.println("Room added: " + roomName);
    }

    public void updateRoom(int roomId, String roomName, int seatingCapacity) {
        roomDAO.updateRoom(roomId, roomName, seatingCapacity);
        System.out.println("Room updated: " + roomName);
    }

    public void deleteRoom(int roomId) {
        roomDAO.deleteRoom(roomId);
        System.out.println("Room deleted: " + roomId);
    }

    public void getAllRooms() {
        roomDAO.getAllRooms().forEach(System.out::println);
    }
}
