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
	private NodeDto firstNodeDto;
	private NodeDto secondNodeDto;
	private String name;
	private Double length;

	@Builder
	public SegmentDto(NodeDto firstNodeDto, NodeDto secondNodeDto, String name, Double length, Integer segId) {
		this.firstNodeDto = firstNodeDto;
		this.secondNodeDto = secondNodeDto;
		this.name = name;
		this.length = length;
		this.segId = segId;
	}

	@JsonPOJOBuilder(withPrefix = "")
	public static class SegmentDtoBuilder {
	}

}
