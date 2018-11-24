package hy.get.string.from.object.rma.controllers;

import hy.get.string.from.object.rma.dto.NodeDto;
import hy.get.string.from.object.rma.dto.SegmentDto;
import hy.get.string.from.object.rma.services.SegmentService;
import net.bedra.maciej.mblogging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SegmentController {

	private static Logger log = Logger.getLogger();

	private SegmentService segmentService;

	@Autowired
	public SegmentController(SegmentService segmentService) {
		this.segmentService = segmentService;
	}


	@RequestMapping(
		path = "/segment",
		method = RequestMethod.POST,
		consumes = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
		produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE
	)


	public ResponseEntity<SegmentDto> CreateSegment(@RequestBody SegmentDto segmentDto) {
		log.info("Start create segment");
		SegmentDto newSegmentDto = segmentService.createSegment(segmentDto);
		log.info("End create segment");
		return ResponseEntity.status(200).body(segmentDto);

	}

}
