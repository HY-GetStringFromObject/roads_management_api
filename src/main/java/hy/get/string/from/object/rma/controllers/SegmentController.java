package hy.get.string.from.object.rma.controllers;

import hy.get.string.from.object.rma.services.SegmentService;
import net.bedra.maciej.mblogging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SegmentController {

	private static Logger log = Logger.getLogger();

	private SegmentService segmentService;

	@Autowired
	public SegmentController(SegmentService segmentService) {
		this.segmentService = segmentService;
	}



	public void SegmentCreate(){
		log.info("Start create segment");



		log.info("End create segment");

	}

}
