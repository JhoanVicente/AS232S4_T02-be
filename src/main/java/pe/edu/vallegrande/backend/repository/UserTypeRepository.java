package pe.edu.vallegrande.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.backend.model.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {
}