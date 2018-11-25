package hy.get.string.from.object.rma.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonDeserialize(builder = SegmentDto.SegmentDtoBuilder.class)
public class SegmentDto {

	private Integer segId;
	private NodeDto firstNode;
	private NodeDto secondNode;
	private String name;
	private Integer length;

	@Builder
	public SegmentDto(NodeDto firstNode, NodeDto secondNode, String name, Integer length, Integer segId) {
		this.firstNode = firstNode;
		this.secondNode = secondNode;
		this.name = name;
		this.length = length;
		this.segId = segId;
	}

	@JsonPOJOBuilder(withPrefix = "")
	public static class SegmentDtoBuilder {
	}

}
