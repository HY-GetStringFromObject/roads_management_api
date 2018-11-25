package hy.get.string.from.object.rma.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import hy.get.string.from.object.rma.entities.Node;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonDeserialize(builder = RouteDto.RouteDtoBuilder.class)
public class RouteDto {

	private NodeDto startNode;
	private NodeDto endNode;
	private LimitationDto LimDto;

	@Builder
	public RouteDto(NodeDto startNode, NodeDto endNode, LimitationDto limDto) {
		this.startNode = startNode;
		this.endNode = endNode;
		LimDto = limDto;
	}

	@JsonPOJOBuilder(withPrefix = "")
	public static class RouteDtoBuilder {
	}

}