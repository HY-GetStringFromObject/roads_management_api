package hy.get.string.from.object.rma.repositories;

import hy.get.string.from.object.rma.entities.Node;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends CrudRepository<Node, Integer> {

	Node findByLatAndLng(Double lat, Double lng);
	Node findByNodId(Integer nodId);


}
