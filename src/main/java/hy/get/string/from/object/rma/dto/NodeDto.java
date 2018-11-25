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
	private Double lat;
	private Integer nodId;

	@Builder
	public NodeDto(Double lat, Double lng, Integer nodId) {
		this.lat = lat;
		this.lng = lng;
		this.nodId = nodId;
	}

	@JsonPOJOBuilder(withPrefix = "")
	public static class NodeDtoBuilder {
	}

}
