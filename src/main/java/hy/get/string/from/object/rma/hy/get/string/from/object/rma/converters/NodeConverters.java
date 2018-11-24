package hy.get.string.from.object.rma.hy.get.string.from.object.rma.converters;

import hy.get.string.from.object.rma.dto.NodeDto;
import hy.get.string.from.object.rma.entities.Node;

public class NodeConverters {
	public static NodeDto convertToDto(Node n) {
		if (n == null) {
			return null;
		}

		return NodeDto.builder()
			.length(n.getLength())
			.width(n.getWidth())
			.build();
	}
}
