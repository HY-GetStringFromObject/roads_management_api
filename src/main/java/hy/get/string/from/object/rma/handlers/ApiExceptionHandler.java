package hy.get.string.from.object.rma.handlers;

import hy.get.string.from.object.rma.dto.ApiError;
import hy.get.string.from.object.rma.exceptions.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiError> handleApiException(ApiException ex) {
		ApiError apiError = ApiError.builder()
			.status(ex.getStatus())
			.message(ex.getMessage())
			.errors(ex.getErrors())
			.build();

		return ResponseEntity.status(ex.getStatus()).body(apiError);
	}

}
