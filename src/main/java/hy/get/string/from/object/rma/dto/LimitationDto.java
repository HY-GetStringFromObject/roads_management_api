package hy.get.string.from.object.rma.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@JsonDeserialize(builder = LimitationDto.LimitationDtoBuilder.class)
public class LimitationDto {

	private Integer limId;
	private Double maxWeight;
	private Timestamp fromDate;
	private Timestamp toDate;

	@Builder
	public LimitationDto(Integer limId, Double maxWeight, Timestamp fromDate, Timestamp toDate) {
		this.limId = limId;
		this.maxWeight = maxWeight;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	@JsonPOJOBuilder(withPrefix = "")
	public static class LimitationDtoBuilder {
	}


}
