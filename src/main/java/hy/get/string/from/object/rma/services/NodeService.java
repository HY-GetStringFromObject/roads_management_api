package hy.get.string.from.object.rma.services;

import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.LatLng;
import hy.get.string.from.object.rma.dto.ApiErrorDetail;
import hy.get.string.from.object.rma.exceptions.ApiException;
import hy.get.string.from.object.rma.handlers.ResponseHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.bedra.maciej.mblogging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NodeService {

	private static Logger log = Logger.getLogger();

	@Value("${google.secretkey}")
	private String googleSecret;

	@Value("${google.api.url}")
	private String googleApiUrl;

	public List<LatLng> getConvertedPolyline(String origin, String destination) {
		try {
			List<ApiErrorDetail> errors = new ArrayList<>();
			String[] coordinatesStart;
			String[] coordinatesEnd;

			if (origin == null || origin.trim().isEmpty()) {
				errors.add(new ApiErrorDetail("Wrong origin value", new String[]{"origin"}));
			} else {
				coordinatesStart = origin.split(",");

				if (coordinatesStart.length != 2) {
					errors.add(new ApiErrorDetail("Wrong number of origin coordinates", new String[]{"origin"}));
				}
			}

			if (destination == null || destination.trim().isEmpty()) {
				errors.add(new ApiErrorDetail("Wrong destination value", new String[]{"destination"}));
			} else {
				coordinatesEnd = destination.split(",");

				if (coordinatesEnd.length != 2) {
					errors.add(new ApiErrorDetail("Wrong number of destination coordinates", new String[]{"destination"}));
				}
			}

			if (errors.isEmpty()) {

				RestTemplate rt = new RestTemplate();
				ResponseEntity response = rt.getForEntity(
					googleApiUrl + "?origin=" + origin + "&destination=" + destination + "&key=" + googleSecret,
					Object.class
				);
				Map<String, Object> responseBody = (Map<String, Object>) ResponseHandler.handleResponse(response, "Google services error");
				ArrayList<Object> routes = (ArrayList<Object>) responseBody.get("routes");
				Map<String, Object> routesArrays = (Map<String, Object>) routes.get(0);
				Map<String, Object> polyline = (Map<String, Object>) routesArrays.get("overview_polyline");
				String points = (String) polyline.get("points");

				return PolylineEncoding.decode(points);
			} else {
				throw new ApiException(400, "Validation error", errors);
			}
		} catch (ApiException ae) {
			throw ae;
		} catch (Exception ex) {
			log.error("Internal error", ex);
			throw new ApiException(500, "Internal error");
		}
	}

}
