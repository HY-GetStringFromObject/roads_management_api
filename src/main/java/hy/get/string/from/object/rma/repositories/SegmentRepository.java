package hy.get.string.from.object.rma.repositories;

import hy.get.string.from.object.rma.entities.Segment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentRepository extends CrudRepository<Segment, Integer> {

	Segment findBySegId(Integer segId);

}
