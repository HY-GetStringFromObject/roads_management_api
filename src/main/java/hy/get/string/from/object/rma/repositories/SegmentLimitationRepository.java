package hy.get.string.from.object.rma.repositories;

import hy.get.string.from.object.rma.entities.Segment;
import hy.get.string.from.object.rma.entities.SegmentLimitation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SegmentLimitationRepository extends CrudRepository<SegmentLimitation, Integer> {
	List<SegmentLimitation> findBySegmentBySegSegId(Segment seg);
}
