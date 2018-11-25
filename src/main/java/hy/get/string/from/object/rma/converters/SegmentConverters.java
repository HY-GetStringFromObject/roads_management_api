package hy.get.string.from.object.rma.converters;

import hy.get.string.from.object.rma.dto.SegmentDto;
import hy.get.string.from.object.rma.entities.Segment;

public class SegmentConverters {

	public static SegmentDto convertToDto(Segment p) {
		return SegmentDto.builder()
			.firstNode(NodeConverters.convertToDto(p.getNodeByFirNode()))
			.secondNode(NodeConverters.convertToDto((p.getNodeBySecNode())))
			.length(p.getLength())
			.name(p.getName())
			.segId(p.getSegId())
			.build();
	}

}
