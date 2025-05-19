package pe.edu.vallegrande.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import pe.edu.vallegrande.backend.model.RestaurantTable;
import pe.edu.vallegrande.backend.service.TableService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/api/table")
@Tag(name = "Table API", description = "API para la gestión de mesas")
public class TableRest {

    private final TableService tableService;

    @Autowired
    public TableRest(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping
    @Operation(summary = "Obtener todas las mesas", description = "Obtiene la lista de todas las mesas")
    public List<RestaurantTable> getAll() {
        return tableService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener mesa por ID", description = "Obtiene una mesa por su ID")
    public Optional<RestaurantTable> findById(@PathVariable Long id) {
        return tableService.findById(id);
    }

    @PostMapping("/save")
    @Operation(summary = "Guardar mesa", description = "Guarda una nueva mesa")
    public RestaurantTable save(@RequestBody RestaurantTable table) {
        return tableService.save(table);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Actualizar mesa", description = "Actualiza una mesa existente")
    public RestaurantTable update(@PathVariable Long id, @RequestBody RestaurantTable table) {
        return tableService.update(table);
    }

    @PatchMapping("/delete/{id}")
    @Operation(summary = "Eliminar mesa lógicamente", description = "Elimina lógicamente una mesa")
    public RestaurantTable delete(@PathVariable Long id) {
        return tableService.delete(id);
    }

    @PatchMapping("/restore/{id}")
    @Operation(summary = "Restaurar mesa", description = "Restaura una mesa eliminada lógicamente")
    public RestaurantTable restore(@PathVariable Long id) {
        return tableService.restore(id);
    }
}