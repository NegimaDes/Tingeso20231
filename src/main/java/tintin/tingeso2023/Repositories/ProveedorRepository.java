package tintin.tingeso2023.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tintin.tingeso2023.Entities.ProveedorEntity;

import java.util.Optional;

@Repository
public interface ProveedorRepository extends CrudRepository<ProveedorEntity, Integer> {

    Optional<ProveedorEntity> findByNombre(String Name);
}
