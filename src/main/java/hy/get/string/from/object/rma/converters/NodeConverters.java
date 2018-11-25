package hy.get.string.from.object.rma.converters;

import hy.get.string.from.object.rma.dto.NodeDto;
import hy.get.string.from.object.rma.entities.Node;

public class NodeConverters {

	public static NodeDto convertToDto(Node n) {
		return NodeDto.builder()
			.lng(n.getLng())
			.lat(n.getLat())
			.nodId(n.getNodId())
			.build();
	}

}
