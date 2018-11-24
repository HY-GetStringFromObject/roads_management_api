package hy.get.string.from.object.rma.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonDeserialize(builder = ApiError.ApiErrorBuilder.class)
public class ApiError {

	private Integer status;
	private String message;
	private List<ApiErrorDetail> errors;

	@Builder
	public ApiError(Integer status, String message, List<ApiErrorDetail> errors) {
		this.status = status;
		this.message = message;
		this.errors = errors;
	}

	@JsonPOJOBuilder(withPrefix = "")
	public static class ApiErrorBuilder {

	}

}
