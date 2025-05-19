package pe.edu.vallegrande.backend.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.backend.dto.UserCredentialsDTO;
import pe.edu.vallegrande.backend.model.RestaurantUser;
import pe.edu.vallegrande.backend.service.RestaurantUserService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/api/user")
@Tag(name = "User API", description = "API para la gestión de usuarios")
public class RestaurantUserRest {

    private final RestaurantUserService userService;

    @Autowired
    public RestaurantUserRest(RestaurantUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los usuarios", description = "Obtiene la lista de todos los usuarios")
    public ResponseEntity<List<RestaurantUser>> getAll() {
        List<RestaurantUser> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por ID", description = "Obtiene un usuario por su ID")
    public ResponseEntity<RestaurantUser> findById(@PathVariable Long id) {
        Optional<RestaurantUser> user = userService.findById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear usuario", description = "Crea un nuevo usuario")
    public ResponseEntity<RestaurantUser> save(@RequestBody RestaurantUser user) {
        RestaurantUser savedUser = userService.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario", description = "Actualiza un usuario existente")
    public ResponseEntity<RestaurantUser> update(@PathVariable Long id, @RequestBody RestaurantUser user) {
        if (user.getId() == null || !user.getId().equals(id)) {
            user.setId(id);
        }
        RestaurantUser updatedUser = userService.update(user);
        return ResponseEntity.ok(updatedUser);
    }
    
    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión", description = "Valida las credenciales de un usuario")
    public ResponseEntity<RestaurantUser> login(@RequestBody UserCredentialsDTO credentials) {
        Optional<RestaurantUser> user = userService.findByUsernameAndPassword(
                credentials.getUsername(), credentials.getPassword());
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}