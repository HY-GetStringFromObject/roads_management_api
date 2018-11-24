package hy.get.string.from.object.rma.controllers;

<<<<<<<HEAD
	=======
import hy.get.string.from.object.rma.dto.NodeDto;
>>>>>>>38b716ed77cadcacd73d04539454f269d82b0f45
import hy.get.string.from.object.rma.dto.SegmentDto;
import hy.get.string.from.object.rma.services.SegmentService;
import net.bedra.maciej.mblogging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
<<<<<<<HEAD
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;
import javax.xml.ws.Response;
import java.util.List;
=======
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
>>>>>>>38b716ed77cadcacd73d04539454f269d82b0f45

@Controller
public class SegmentController {

	private static Logger log = Logger.getLogger();

	private SegmentService segmentService;

	@Autowired
	public SegmentController(SegmentService segmentService) {
		this.segmentService = segmentService;
	}

	@RequestMapping(
		path = "/segment/{segId}",
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public ResponseEntity<SegmentDto> getSegmentById(@PathVariable(name = "segId") Integer segId) {

		SegmentDto singleSegment = segmentService.getSingleSegment(segId);
		if (singleSegment == null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(singleSegment);
		}
	}

	@RequestMapping(
		path = "/segment",
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public ResponseEntity<List<SegmentDto>> getSegmentById() {

		List<SegmentDto> segmentList = segmentService.getAllSegments();
		if (segmentList == null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(segmentList);
		}
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
