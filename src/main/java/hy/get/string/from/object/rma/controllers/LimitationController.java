package hy.get.string.from.object.rma.controllers;

import hy.get.string.from.object.rma.dto.LimitationDto;
import hy.get.string.from.object.rma.services.LimitationService;
import net.bedra.maciej.mblogging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class LimitationController {

	private static Logger log = Logger.getLogger();

	private LimitationService limitationService;

	@Autowired
	public LimitationController(LimitationService limitationService) {
		this.limitationService = limitationService;
	}

	@RequestMapping(
		path = "/limitation/{limId}",
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public ResponseEntity<LimitationDto> getLimitationById(
		@PathVariable(name = "limId") Integer limId
	) {
		log.info("Start of getLimitationById");
		LimitationDto limitation = limitationService.getLimitationById(limId);
		log.info("End of getLimitationById");

		if (limitation == null) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(limitation);
		}
	}

	@RequestMapping(
		path = "/limitation",
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public ResponseEntity<List<LimitationDto>> getAllLimitations() {
		log.info("Start of getAllLimitations");
		List<LimitationDto> allLimitation = limitationService.getAllLimitations();
		log.info("End of getAllLimitations");

		if (allLimitation == null || allLimitation.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(allLimitation);
		}
	}

}
