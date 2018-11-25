package hy.get.string.from.object.rma.services;

import com.google.maps.model.LatLng;
import hy.get.string.from.object.rma.dto.ApiErrorDetail;
import hy.get.string.from.object.rma.entities.Node;
import hy.get.string.from.object.rma.entities.Segment;
import hy.get.string.from.object.rma.exceptions.ApiException;
import hy.get.string.from.object.rma.repositories.NodeRepository;
import java.util.ArrayList;
import java.util.List;
import net.bedra.maciej.mblogging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService {

	private static Logger log = Logger.getLogger();

	private NodeRepository nodeRepository;

	@Autowired
	public RouteService(NodeRepository nodeRepository) {
		this.nodeRepository = nodeRepository;
	}

	public List<LatLng> getBestRoute(Integer startNodId, Integer endNodId) {
		try {
			List<ApiErrorDetail> errors = new ArrayList<>();
			Node startNode = nodeRepository.findByNodId(startNodId);

			if (startNode == null) {
				errors.add(new ApiErrorDetail("Start node do not exists", new String[]{"startNodId"}));
			}

			Node endNode = nodeRepository.findByNodId(endNodId);

			if (endNode == null) {
				errors.add(new ApiErrorDetail("End node do not exists", new String[]{"endNodId"}));
			}

//			if (errors.isEmpty()) {
//				Segment startSegment =
//			} else {
//				throw new ApiException(400, "Validation error", errors);
//			}

			return null;
		} catch (ApiException ae) {
			throw ae;
		} catch (Exception ex) {
			log.error("Internal error", ex);
			throw new ApiException(500, "Internal error");
		}
	}

}
