package pe.edu.vallegrande.backend.service;

import pe.edu.vallegrande.backend.model.RestaurantUser;
import java.util.List;
import java.util.Optional;

public interface RestaurantUserService {
    List<RestaurantUser> getAll();
    Optional<RestaurantUser> findById(Long id);
    RestaurantUser save(RestaurantUser user);
    RestaurantUser update(RestaurantUser user);
    Optional<RestaurantUser> findByUsernameAndPassword(String username, String password);
}