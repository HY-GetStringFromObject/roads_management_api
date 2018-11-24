package hy.get.string.from.object.rma.controllers;

import com.google.maps.model.LatLng;
import hy.get.string.from.object.rma.services.NodeService;
import java.util.List;
import net.bedra.maciej.mblogging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NodeController {

	private static Logger log = Logger.getLogger();

	private NodeService nodeService;

	@Autowired
	public NodeController(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	@RequestMapping(
		path = "/polyline",
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public ResponseEntity<List<LatLng>> getConvertedPolyline(
		@RequestParam(value = "origin", defaultValue = "") String origin,
		@RequestParam(value = "destination", defaultValue = "") String destination
	) {
		log.info("Start of getConvertedPolyline");
		List<LatLng> points = nodeService.getConvertedPolyline(origin, destination);
		log.info("End of getConvertedPolyline");

		if (points == null || points.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(points);
		}
	}

}
