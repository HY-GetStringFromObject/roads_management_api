package hy.get.string.from.object.rma.handlers;

import hy.get.string.from.object.rma.exceptions.ApiException;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

	public static Object handleResponse(ResponseEntity response, String errorMessage) {
		switch (response.getStatusCodeValue()) {
			case 200:
				return response.getBody();

			default:
				throw new ApiException(400, errorMessage);
		}
	}

}
