package hy.get.string.from.object.rma.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.maps.model.LatLng;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonDeserialize(builder = PolylineDto.PolylineDtoBuilder.class)
public class PolylineDto {

	private List<LatLng> polyline;
	private Integer length;

	@Builder
	public PolylineDto(List<LatLng> polyline, Integer length) {
		this.polyline = polyline;
		this.length = length;
	}

	@JsonPOJOBuilder(withPrefix = "")
	public static class PolylineDtoBuilder {

	}

}
