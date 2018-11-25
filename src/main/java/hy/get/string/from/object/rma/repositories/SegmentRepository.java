package hy.get.string.from.object.rma.repositories;

import hy.get.string.from.object.rma.entities.Node;
import hy.get.string.from.object.rma.entities.Segment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SegmentRepository extends CrudRepository<Segment, Integer> {

	Segment findBySegId(Integer segId);
	Segment findByName(String name);
	List<Segment> findByNodeByFirNode(Node n);
	List<Segment> findByNodeBySecNode(Node n);

}
