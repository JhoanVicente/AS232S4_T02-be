package pe.edu.vallegrande.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.backend.model.RestaurantTable;
import pe.edu.vallegrande.backend.repository.TableRepository;
import pe.edu.vallegrande.backend.service.TableService;
import java.util.List;
import java.util.Optional;

@Service
public class TableServiceImpl implements TableService {

    private final TableRepository tableRepository;

    @Autowired
    public TableServiceImpl(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public List<RestaurantTable> getAll() {
        return tableRepository.findAll();
    }

    @Override
    public Optional<RestaurantTable> findById(Long id) {
        return tableRepository.findById(id);
    }

    @Override
    public RestaurantTable save(RestaurantTable table) {
        table.setState("A");
        return tableRepository.save(table);
    }

    @Override
    public RestaurantTable update(RestaurantTable table) {
        return tableRepository.save(table);
    }

    @Override
    public RestaurantTable delete(Long id) {
        RestaurantTable table = tableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));
        table.setState("I");
        return tableRepository.save(table);
    }

    @Override
    public RestaurantTable restore(Long id) {
        RestaurantTable table = tableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));
        table.setState("A");
        return tableRepository.save(table);
    }
}