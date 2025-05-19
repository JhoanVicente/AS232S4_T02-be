package pe.edu.vallegrande.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.backend.model.RestaurantUser;
import pe.edu.vallegrande.backend.model.UserType;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantUserRepository extends JpaRepository<RestaurantUser, Long> {
    List<RestaurantUser> findByState(String state);
    List<RestaurantUser> findByUserType(UserType userType);
    Optional<RestaurantUser> findByUsernameAndPassword(String username, String password);
}