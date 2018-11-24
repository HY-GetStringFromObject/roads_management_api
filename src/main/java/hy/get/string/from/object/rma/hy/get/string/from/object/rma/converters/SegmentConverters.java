package hy.get.string.from.object.rma.hy.get.string.from.object.rma.converters;

import hy.get.string.from.object.rma.dto.SegmentDto;
import hy.get.string.from.object.rma.entities.Segment;

public class SegmentConverters {
	public static SegmentDto convertToDto(Segment p) {
		if (p == null) {
			return null;
		}

		return SegmentDto.builder()
			.firstNodeDto(NodeConverters.convertToDto(p.getNodeByFirNode()))
			.secondNodeDto(NodeConverters.convertToDto((p.getNodeBySecNode())))
			.length(p.getLength())
			.name(p.getName())
			.build();
	}
}
