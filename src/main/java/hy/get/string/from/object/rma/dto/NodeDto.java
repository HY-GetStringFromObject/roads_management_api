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

	private Double lng;
	private Double lag;
	private Integer idNode;

	@Builder
	public NodeDto(Double lag, Double lng, Integer idNode) {
		this.lag = lag;
		this.lng = lng;
		this.idNode = idNode;
	}

	@JsonPOJOBuilder(withPrefix = "")
	public static class NodeDtoBuilder {
	}
}
