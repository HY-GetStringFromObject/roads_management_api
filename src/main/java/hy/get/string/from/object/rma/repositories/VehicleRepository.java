package hy.get.string.from.object.rma.repositories;

import hy.get.string.from.object.rma.entities.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle , Integer> {
}
