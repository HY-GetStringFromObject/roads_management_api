package hy.get.string.from.object.rma.controllers;

import com.google.maps.model.LatLng;
import hy.get.string.from.object.rma.dto.PolylineDto;
import hy.get.string.from.object.rma.services.NodeService;

import java.util.List;

import net.bedra.maciej.mblogging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import hy.get.string.from.object.rma.dto.NodeDto;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
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
	public ResponseEntity<PolylineDto> getConvertedPolyline(
		@RequestParam(value = "origin", defaultValue = "") String origin,
		@RequestParam(value = "destination", defaultValue = "") String destination
	) {
		log.info("Start of getConvertedPolyline");
		PolylineDto polyline = nodeService.getConvertedPolyline(origin, destination);
		log.info("End of getConvertedPolyline");

		if (polyline == null) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(polyline);
		}
	}

	@RequestMapping(
		path = "/node",
		method = RequestMethod.POST,
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public ResponseEntity<NodeDto> createNode(@RequestBody NodeDto nodeDto) {
		log.info("Start create node");
		NodeDto node = nodeService.createNode(nodeDto);
		log.info("End create node");

		return ResponseEntity.status(200).body(node);
	}

	@RequestMapping(
		path = "/node",
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public ResponseEntity<List<NodeDto>> getAllNodes() {
		log.info("Start get all nodes");
		List<NodeDto> allNode = nodeService.getAllNodes();
		log.info("End get all nodes");

		if (allNode == null || allNode.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(allNode);
		}
	}

	@RequestMapping(
		path = "/node/{nodId}",
		method = RequestMethod.DELETE
	)
	public ResponseEntity<Integer> deleteNode(@PathVariable(name = "nodId") Integer nodId) {
		log.info("Start get all nodes");
		Integer deleteNodeId = nodeService.deleteNode(nodId);
		log.info("End get all nodes");

		if (deleteNodeId != null) {
			return ResponseEntity.status(200).body(deleteNodeId);
		} else {
			return ResponseEntity.status(204).build();
		}
	}

}