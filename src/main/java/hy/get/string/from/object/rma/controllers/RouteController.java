package hy.get.string.from.object.rma.controllers;

import net.bedra.maciej.mblogging.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
public class RouteController {

	private static Logger log = Logger.getLogger();

	@RequestMapping(
		path = "/route/{first_nod_id}/{second_nod_id}",
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public ResponseEntity getBestRoute(
		@PathVariable(name = "first_nod_id") Integer startNodId,
		@PathVariable(name = "second_nod_id") Integer endNodId
	) {
		return null;
	}

}
