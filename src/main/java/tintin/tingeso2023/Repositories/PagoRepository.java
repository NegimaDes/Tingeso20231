package tintin.tingeso2023.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tintin.tingeso2023.Entities.PagoEntity;

@Repository
public interface PagoRepository extends CrudRepository<PagoEntity, Integer> {
}
