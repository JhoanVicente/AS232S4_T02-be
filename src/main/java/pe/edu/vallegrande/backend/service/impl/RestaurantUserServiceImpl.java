package pe.edu.vallegrande.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.backend.model.RestaurantUser;
import pe.edu.vallegrande.backend.repository.RestaurantUserRepository;
import pe.edu.vallegrande.backend.service.RestaurantUserService;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantUserServiceImpl implements RestaurantUserService {

    private final RestaurantUserRepository userRepository;

    @Autowired
    public RestaurantUserServiceImpl(RestaurantUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<RestaurantUser> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<RestaurantUser> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public RestaurantUser save(RestaurantUser user) {
        user.setState("A");
        return userRepository.save(user);
    }

    @Override
    public RestaurantUser update(RestaurantUser user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<RestaurantUser> findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}