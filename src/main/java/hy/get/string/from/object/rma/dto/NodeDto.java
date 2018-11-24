package hy.get.string.from.object.rma.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonDeserialize(builder = NodeDto.NodeDtoBuilder.class)
public class NodeDto {

	private Double length;
	private Double width;

	@Builder
	public NodeDto(Double length, Double width) {
		this.length = length;
		this.width = width;
	}

	@JsonPOJOBuilder(withPrefix = "")
	public static class NodeDtoBuilder {
	}
}
