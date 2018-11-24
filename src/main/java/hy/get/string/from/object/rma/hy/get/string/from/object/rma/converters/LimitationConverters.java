package hy.get.string.from.object.rma.hy.get.string.from.object.rma.converters;

import hy.get.string.from.object.rma.dto.LimitationDto;
import hy.get.string.from.object.rma.entities.Limitation;

public class LimitationConverters {

	public static LimitationDto convertToDto(Limitation p) {

		return LimitationDto.builder()
			.fromDate(p.getFromDate())
			.limId(p.getLimId())
			.maxWeight(p.getMaxWeight())
			.build();
	}
}
