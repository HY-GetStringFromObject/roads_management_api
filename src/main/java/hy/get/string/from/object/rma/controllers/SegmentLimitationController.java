package hy.get.string.from.object.rma.controllers;

import hy.get.string.from.object.rma.dto.SegmentLimitationDto;
import hy.get.string.from.object.rma.services.SegmentLimitationService;
import net.bedra.maciej.mblogging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SegmentLimitationController {

	private static Logger log = Logger.getLogger();

	private SegmentLimitationService segmentLimitationService;

	@Autowired
	public SegmentLimitationController(SegmentLimitationService segmentLimitationService) {
		this.segmentLimitationService = segmentLimitationService;
	}

	@RequestMapping(
		path = "/segment/{seg_id}/limitation/{lim_id}",
		method = RequestMethod.POST,
		consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public ResponseEntity<SegmentLimitationDto> createSegmentLimitation(
		@PathVariable(name = "seg_id") Integer segId,
		@PathVariable(name = "lim_id") Integer limId
	) {
		log.info("Start of createSegmentLimitation");
		SegmentLimitationDto sld = segmentLimitationService.createSegmentLimitation(segId, limId);
		log.info("End of createSegmentLimitation");

		if (sld == null) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(sld);
		}
	}

}
