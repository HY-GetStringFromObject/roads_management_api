package hy.get.string.from.object.rma.exceptions;

import hy.get.string.from.object.rma.dto.ApiErrorDetail;
import java.util.List;
import lombok.Getter;

public class ApiException extends RuntimeException {

	@Getter
	private Integer status;

	@Getter
	private String message;

	@Getter
	private List<ApiErrorDetail> errors;

	public ApiException(Integer status, String message, List<ApiErrorDetail> errors) {
		this.status = status;
		this.message = message;
		this.errors = errors;
	}

	public ApiException(Integer status, String message) {
		this.status = status;
		this.message = message;
		this.errors = null;
	}

}
