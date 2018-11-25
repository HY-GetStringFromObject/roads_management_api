package hy.get.string.from.object.rma.controllers;

import hy.get.string.from.object.rma.dto.NodeDto;
import hy.get.string.from.object.rma.entities.Node;
import hy.get.string.from.object.rma.services.NodeService;
import hy.get.string.from.object.rma.services.SegmentService;
import net.bedra.maciej.mblogging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class NodeController {

	private static Logger log = Logger.getLogger();

	private NodeService nodeService;

	@Autowired
	public NodeController(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	@RequestMapping(
		path = "/node",
		method = RequestMethod.POST,
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public ResponseEntity<NodeDto> createNode(@RequestBody NodeDto nodeDto){

		log.info("Start create node");
		NodeDto node = nodeService.createNode(nodeDto);

		log.info("End create node");
		return ResponseEntity.status(200).body(node);

	}


}
