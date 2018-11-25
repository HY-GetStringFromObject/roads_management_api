package hy.get.string.from.object.rma.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonDeserialize(builder = SegmentLimitationDto.SegmentLimitationDtoBuilder.class)
public class SegmentLimitationDto {

	private Integer segLimId;
	private Integer segSegId;
	private Integer limLimId;

	@Builder
	public SegmentLimitationDto(Integer segSegId, Integer limLimId, Integer segLimId) {
		this.segSegId = segSegId;
		this.limLimId = limLimId;
		this.segLimId = segLimId;
	}

	@JsonPOJOBuilder(withPrefix = "")
	public static class SegmentLimitationDtoBuilder {

	}

}
