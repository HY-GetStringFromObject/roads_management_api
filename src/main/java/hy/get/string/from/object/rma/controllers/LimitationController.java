package hy.get.string.from.object.rma.controllers;

import hy.get.string.from.object.rma.dto.LimitationDto;
import hy.get.string.from.object.rma.services.LimitationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LimitationController {
	private LimitationService limitationService;

	public LimitationController(LimitationService limitationService) {
		this.limitationService = limitationService;
	}

	@RequestMapping(
		path = "/limitation/{limId}",
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public ResponseEntity<LimitationDto> getLimitationById(@PathVariable(name = "limId") Integer limId) {
		LimitationDto limitation = limitationService.getLimitationById(limId);
		if(limitation == null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(limitation);
		}
	}

	@RequestMapping(
		path = "/limitation",
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public ResponseEntity<List<LimitationDto>> getAllLimitation() {
		List<LimitationDto> allLimitation = limitationService.getAllLimitation();
		if(allLimitation == null || allLimitation.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(allLimitation);
		}
	}
}
