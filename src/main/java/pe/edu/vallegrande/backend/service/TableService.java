package pe.edu.vallegrande.backend.service;

import pe.edu.vallegrande.backend.model.RestaurantTable;
import java.util.List;
import java.util.Optional;

public interface TableService {
    List<RestaurantTable> getAll();
    Optional<RestaurantTable> findById(Long id);
    RestaurantTable save(RestaurantTable table);
    RestaurantTable update(RestaurantTable table);
    RestaurantTable delete(Long id);
    RestaurantTable restore(Long id);
}