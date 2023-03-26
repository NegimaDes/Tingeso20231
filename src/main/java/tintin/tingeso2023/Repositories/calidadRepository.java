package tintin.tingeso2023.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tintin.tingeso2023.Entities.pagoEntity;

@Repository
public interface calidadRepository extends CrudRepository<pagoEntity, Integer> {
}
