package tintin.tingeso2023.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tintin.tingeso2023.entities.ProveedorEntity;

import java.util.Optional;

@Repository
public interface ProveedorRepository extends CrudRepository<ProveedorEntity, Integer> {

    Optional<ProveedorEntity> findByNombre(String name);
}
