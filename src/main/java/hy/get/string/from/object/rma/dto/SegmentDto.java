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

	private FirstNodeDto firstNodeDto;
	private SecondNodeDto secondNodeDto;
	private String name;
	private Double length;

	@Builder
	public SegmentDto(FirstNodeDto firstNodeDto, SecondNodeDto secondNodeDto, String name, Double length) {
		this.firstNodeDto = firstNodeDto;
		this.secondNodeDto = secondNodeDto;
		this.name = name;
		this.length = length;
	}

	@JsonPOJOBuilder(withPrefix = "")
	public static class SegmentDtoBuilder {

	}

}
