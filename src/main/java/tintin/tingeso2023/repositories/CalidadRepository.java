package tintin.tingeso2023.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tintin.tingeso2023.entities.CalidadEntity;

@Repository
public interface CalidadRepository extends CrudRepository<CalidadEntity, Integer> {
}
