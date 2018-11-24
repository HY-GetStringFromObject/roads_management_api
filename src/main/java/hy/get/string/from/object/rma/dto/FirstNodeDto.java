package hy.get.string.from.object.rma.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonDeserialize(builder = FirstNodeDto.FirstNodeDtoBuilder.class)
public class FirstNodeDto {

	private Double length;
	private Double width;

	@Builder
	public FirstNodeDto(Double length, Double width) {
		this.length = length;
		this.width = width;
	}

	@JsonPOJOBuilder(withPrefix = "")
	public static class FirstNodeDtoBuilder {
	}
}
