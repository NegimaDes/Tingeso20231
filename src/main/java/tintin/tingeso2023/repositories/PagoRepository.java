package tintin.tingeso2023.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tintin.tingeso2023.entities.PagoEntity;

@Repository
public interface PagoRepository extends CrudRepository<PagoEntity, Integer> {
}
