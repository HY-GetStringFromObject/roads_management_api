package hy.get.string.from.object.rma.controllers;

import hy.get.string.from.object.rma.repositories.SegmentRepository;
import net.bedra.maciej.mblogging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SegmentController {

	private static Logger log = Logger.getLogger();

	private SegmentRepository segmentRepository;

	@Autowired
	public SegmentController(SegmentRepository segmentRepository) {
		this.segmentRepository = segmentRepository;
	}

	public void SegmentCreate(){
		log.info("Start create segment");



		log.info("End create segment");

	}

}
