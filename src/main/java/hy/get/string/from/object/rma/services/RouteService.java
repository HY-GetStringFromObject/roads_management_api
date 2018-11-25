package hy.get.string.from.object.rma.services;

import com.google.maps.model.LatLng;
import hy.get.string.from.object.rma.dto.ApiError;
import hy.get.string.from.object.rma.dto.ApiErrorDetail;
import hy.get.string.from.object.rma.dto.PolylineDto;
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

	private NodeService nodeService;

	@Autowired
	public RouteService(NodeRepository nodeRepository, NodeService nodeService) {

		this.nodeRepository = nodeRepository;
		this.nodeService = nodeService;
	}

	public PolylineDto getBestRoute(Integer startNodId, Integer endNodId) {
		try {

			List<ApiErrorDetail> errorList = new ArrayList<>();
			Node firstNode = nodeRepository.findByNodId(startNodId);
			Node secondNode = nodeRepository.findByNodId(endNodId);

			if (firstNode == null) {
				errorList.add(new ApiErrorDetail("Start node do not existe", new String[]{"startNodId"}));
			}

			if (secondNode == null) {
				errorList.add(new ApiErrorDetail("End node do not existe", new String[]{"endNodId"}));
			}

			if (!errorList.isEmpty()) {
				throw new ApiException(400, "Validation error", errorList);
			} else {

				PolylineDto convertedPolyline = nodeService.getConvertedPolyline(firstNode.getLat().toString() + "," + firstNode.getLng().toString(), secondNode.getLat().toString() + "," + secondNode.getLng().toString());
				return convertedPolyline;

			}
		} catch (ApiException ae) {
			throw ae;
		} catch (Exception ex) {
			log.error("Internal error", ex);
			throw new ApiException(500, "Internal error");
		}
	}

}
