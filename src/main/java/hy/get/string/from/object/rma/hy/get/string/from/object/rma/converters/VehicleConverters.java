package hy.get.string.from.object.rma.hy.get.string.from.object.rma.converters;

import hy.get.string.from.object.rma.dto.VehicleDto;
import hy.get.string.from.object.rma.entities.Vehicle;

public class VehicleConverters {

	public static VehicleDto convertToDto(Vehicle v){
		return VehicleDto.builder()
			.plate(v.getPlate())
			.weight(v.getWeight())
			.build();
	}

}
