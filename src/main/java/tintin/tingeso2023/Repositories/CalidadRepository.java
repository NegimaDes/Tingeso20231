package tintin.tingeso2023.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tintin.tingeso2023.Entities.CalidadEntity;

import java.util.Optional;

@Repository
public interface CalidadRepository extends CrudRepository<CalidadEntity, Integer> {
}
