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
	private Integer idNode;

	@Builder
	public NodeDto(Double length, Double width, Integer idNode) {
		this.length = length;
		this.width = width;
		this.idNode = idNode;
	}

	@JsonPOJOBuilder(withPrefix = "")
	public static class NodeDtoBuilder {
	}
}
