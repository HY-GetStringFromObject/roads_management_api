package hy.get.string.from.object.rma.repositories;

import hy.get.string.from.object.rma.entities.Node;
import org.springframework.data.repository.CrudRepository;

public interface NodeRepository extends CrudRepository<Node, Integer> {

	Node findByLengthAndWidth(Double length, Double width);
}
