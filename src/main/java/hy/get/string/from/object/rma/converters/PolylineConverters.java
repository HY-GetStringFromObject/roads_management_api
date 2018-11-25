package hy.get.string.from.object.rma.converters;

import com.google.maps.model.LatLng;
import hy.get.string.from.object.rma.dto.PolylineDto;
import java.util.List;

public class PolylineConverters {

	public static PolylineDto convertToPolylineDto(List<LatLng> coordinates, Double length) {
		return PolylineDto.builder()
			.polyline(coordinates)
			.length(length)
			.build();
	}

}
