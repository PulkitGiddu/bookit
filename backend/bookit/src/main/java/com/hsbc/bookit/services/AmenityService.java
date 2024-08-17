package com.hsbc.bookit.services;

import com.hsbc.bookit.dao.AmenityDAO;
import com.hsbc.bookit.dao.AmenityDAOImpl;
import com.hsbc.bookit.domain.Amenities;
import com.hsbc.bookit.domain.Users;
import com.hsbc.bookit.exceptions.AccessDeniedException;

public class AmenityService {
    private final AmenityDAO amenityDAO = (AmenityDAO) new AmenityDAOImpl();
    private final LoginService loginService = new LoginService();
    private Users authenticatedUser;

    public AmenityService(String username, String password) {
        authenticatedUser = loginService.login(username, password);
        if (authenticatedUser == null || !"manager".equalsIgnoreCase(authenticatedUser.getRole())) {
            throw new AccessDeniedException("Only managers can perform this action!");
        }
    }

    public void addAmenity(String name, int cost) {
        Amenities amenity = new Amenities(name, cost);
        amenityDAO.addAmenity(amenity);
        System.out.println("Amenity added: " + name);
    }

    public void updateAmenity(int amenityId, String name, int cost) {
        amenityDAO.updateAmenity(amenityId, name, cost);
        System.out.println("Amenity updated: " + name);
    }

    public void deleteAmenity(int amenityId) {
        amenityDAO.deleteAmenity(amenityId);
        System.out.println("Amenity deleted: " + amenityId);
    }

    public void getAllAmenities() {
        amenityDAO.getAllAmenities().forEach(System.out::println);
    }
}
