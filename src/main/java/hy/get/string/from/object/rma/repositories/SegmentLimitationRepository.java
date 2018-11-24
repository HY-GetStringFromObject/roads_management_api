package hy.get.string.from.object.rma.repositories;

import hy.get.string.from.object.rma.entities.SegmentLimitation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentLimitationRepository extends CrudRepository<SegmentLimitation, Integer> {

}
