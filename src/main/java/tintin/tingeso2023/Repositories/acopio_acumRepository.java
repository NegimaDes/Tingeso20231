package tintin.tingeso2023.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tintin.tingeso2023.Entities.acopio_acumEntity;

@Repository
public interface acopio_acumRepository extends CrudRepository<acopio_acumEntity, Integer> {
}
