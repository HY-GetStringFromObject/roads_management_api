package hy.get.string.from.object.rma.controllers;

import hy.get.string.from.object.rma.dto.PolylineDto;
import hy.get.string.from.object.rma.services.NodeService;
import hy.get.string.from.object.rma.dto.RouteDto;
import hy.get.string.from.object.rma.entities.Segment;
import hy.get.string.from.object.rma.services.RouteService;
import net.bedra.maciej.mblogging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
public class RouteController {


	private static Logger log = Logger.getLogger();

	private RouteService routeService;

	@Autowired
	RouteController(RouteService routeService) {
		this.routeService = routeService;
	}

	@RequestMapping(
		path = "/route/{first_nod_id}/{second_nod_id}",
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public ResponseEntity getBestRoute(
		@PathVariable(name = "first_nod_id") Integer startNodId,
		@PathVariable(name = "second_nod_id") Integer endNodId
	) {
		log.info("Start getBestRoute");
		PolylineDto bestRoute = routeService.getBestRoute(startNodId, endNodId);
		log.info("End getBestRoute");

		if (bestRoute != null) {
			return ResponseEntity.status(200).body(bestRoute);
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	@RequestMapping(
		path = "/route",
		method = RequestMethod.POST,
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
		consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public ResponseEntity<Map<Integer, Segment>> getBestRoute(@RequestBody RouteDto route) {
		return ResponseEntity.ok(routeService.getRoute(route.getStartNode(), route.getEndNode(), null));
	}


}
