package hy.get.string.from.object.rma.services;

import com.google.maps.internal.PolylineEncoding;
import hy.get.string.from.object.rma.converters.PolylineConverters;
import hy.get.string.from.object.rma.dto.ApiErrorDetail;
import hy.get.string.from.object.rma.dto.PolylineDto;
import hy.get.string.from.object.rma.exceptions.ApiException;
import hy.get.string.from.object.rma.handlers.ResponseHandler;
import net.bedra.maciej.mblogging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import hy.get.string.from.object.rma.dto.NodeDto;
import hy.get.string.from.object.rma.entities.Node;
import hy.get.string.from.object.rma.converters.NodeConverters;
import hy.get.string.from.object.rma.repositories.NodeRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NodeService {

	private static Logger log = Logger.getLogger();

	@Value("${google.secretkey}")
	private String googleSecret;

	@Value("${google.api.url}")
	private String googleApiUrl;

	private NodeRepository nodeRepository;

	@Autowired
	public NodeService(NodeRepository nodeRepository) {
		this.nodeRepository = nodeRepository;
	}

	public PolylineDto getConvertedPolyline(String origin, String destination) {
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
				String test = googleApiUrl + "?origin=" + origin + "&destination=" + destination + "&key=" + googleSecret;
				RestTemplate rt = new RestTemplate();
				ResponseEntity response = rt.getForEntity(
					googleApiUrl + "?origin=" + origin + "&destination=" + destination + "&key=" + googleSecret,
					Object.class
				);
				Map<String, Object> responseBody = (Map<String, Object>) ResponseHandler.handleResponse(response, "Google services error");
				ArrayList<Object> routes = (ArrayList<Object>) responseBody.get("routes");
				Map<String, Object> routesArrays = (Map<String, Object>) routes.get(0);
				ArrayList<Object> legs = (ArrayList<Object>) routesArrays.get("legs");
				Map<String, Object> legsArrays = (Map<String, Object>) legs.get(0);
				Map<String, Object> distance = (Map<String, Object>) legsArrays.get("distance");
				Map<String, Object> polyline = (Map<String, Object>) routesArrays.get("overview_polyline");
				String points = (String) polyline.get("points");
				Integer distanceValue = (Integer) distance.get("value");

				return PolylineConverters.convertToPolylineDto(PolylineEncoding.decode(points), distanceValue);
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

	public NodeDto createNode(NodeDto nodeDto) {
		try {
			List<ApiErrorDetail> list = new ArrayList<>();

			if (nodeDto.getLng() == null) {
				list.add(new ApiErrorDetail("lng is empty", new String[]{"lng"}));
			}

			if (nodeDto.getLat() == null) {
				list.add(new ApiErrorDetail("lat is empty", new String[]{"lat"}));
			}

			if (!list.isEmpty()) {
				throw new ApiException(400, "Validation errors", list);
			}

			Node findNode = nodeRepository.findByLatAndLng(nodeDto.getLat(), nodeDto.getLng());

			if (findNode == null) {
				findNode = convertToDbNode(nodeDto);
			} else {
				findNode.setModifyDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
			}

			Node save = nodeRepository.save(findNode);
			log.info("Save node to DB id: " + save.getNodId());

			return NodeConverters.convertToDto(save);
		} catch (ApiException ae) {
			throw ae;
		} catch (Exception ex) {
			log.error("Internal error", ex);
			throw new ApiException(500, "Internal error");
		}
	}

	private Node convertToDbNode(NodeDto nodeDto) {
		Date time = Calendar.getInstance().getTime();
		Node node = new Node();
		node.setLat(nodeDto.getLat());
		node.setLng(nodeDto.getLng());
		node.setInsertDate(new Timestamp(time.getTime()));
		node.setModifyDate(new Timestamp(time.getTime()));

		return node;
	}

	public List<NodeDto> getAllNodes() {
		List<Node> allNodes = (List<Node>) nodeRepository.findAll();

		return allNodes.stream()
			.map(NodeConverters::convertToDto)
			.collect(Collectors.toList());
	}

	public Integer deleteNode(Integer nodId) {
		try {
			Optional<Node> findId = nodeRepository.findById(nodId);

			if (findId.isPresent()) {
				nodeRepository.delete(findId.get());

				return nodId;
			}

			return null;
		} catch (ApiException ae) {
			throw ae;
		} catch (Exception ex) {
			log.error("Internal error", ex);
			throw new ApiException(500, "Internal error");
		}
	}

}