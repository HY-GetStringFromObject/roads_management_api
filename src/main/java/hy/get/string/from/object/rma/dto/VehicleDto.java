package hy.get.string.from.object.rma.dto;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonDeserialize(builder = VehicleDto.VehicleDtoBuilder.class)
public class VehicleDto {

	private Double weight;
	private String plate;

	@Builder
	public VehicleDto(Double weight, String plate) {
		this.weight = weight;
		this.plate = plate;
	}

	@JsonPOJOBuilder()
	public static class VehicleDtoBuilder { }
}
