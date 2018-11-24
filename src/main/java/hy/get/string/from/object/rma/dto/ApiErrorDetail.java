package hy.get.string.from.object.rma.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonDeserialize(builder = ApiErrorDetail.ApiErrorDetailBuilder.class)
public class ApiErrorDetail {

	private String message;
	private String[] fields;

	@Builder
	public ApiErrorDetail(String message, String[] fields) {
		this.message = message;
		this.fields = fields;
	}

	@JsonPOJOBuilder(withPrefix = "")
	public static class ApiErrorDetailBuilder {

	}

}
