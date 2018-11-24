package hy.get.string.from.object.rma.repositories;

import hy.get.string.from.object.rma.entities.Limitation;
import org.springframework.data.repository.CrudRepository;

public interface LimitationRepository extends CrudRepository<Limitation, Integer> {

	Limitation findByLimId(Integer limId);

}
