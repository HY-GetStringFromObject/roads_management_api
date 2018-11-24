package hy.get.string.from.object.rma.services;

import hy.get.string.from.object.rma.dto.ApiError;
import hy.get.string.from.object.rma.dto.ApiErrorDetail;
import hy.get.string.from.object.rma.dto.NodeDto;
import hy.get.string.from.object.rma.dto.SegmentDto;
import hy.get.string.from.object.rma.entities.Node;
import hy.get.string.from.object.rma.entities.Segment;
import hy.get.string.from.object.rma.exceptions.ApiException;
import hy.get.string.from.object.rma.hy.get.string.from.object.rma.converters.SegmentConverters;
import hy.get.string.from.object.rma.repositories.NodeRepository;
import hy.get.string.from.object.rma.repositories.SegmentRepository;
import net.bedra.maciej.mblogging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SegmentService {

	private SegmentRepository segmentRepository;

	private NodeRepository nodeRepository;

	private static Logger log = Logger.getLogger();

	@Autowired
	public SegmentService(SegmentRepository segmentRepository, NodeRepository nodeRepository) {
		this.segmentRepository = segmentRepository;
		this.nodeRepository = nodeRepository;
	}

	public SegmentDto getSingleSegment(Integer segmentId) {

		Optional<Segment> segmentById = segmentRepository.findById(segmentId);
		Segment segment = segmentById.orElse(null);

		return SegmentConverters.convertToDto(segment);
	}

	public List<SegmentDto> getAllSegments() {
		Iterable<Segment> all = segmentRepository.findAll();
		Integer size = 0;
		if (all instanceof Collection) {
			size = ((Collection) all).size();
		}
		if (size == 0) {
			return null;
		}

		return StreamSupport.stream(all.spliterator(), false)
			.map(p -> SegmentConverters.convertToDto(p))
			.collect(Collectors.toList());
	}

	public SegmentDto createSegment(SegmentDto segmentDto) {

		try {

			Node firstNode = findNode(segmentDto.getFirstNodeDto(), segmentDto.getFirstNodeDto().getLength(), segmentDto.getFirstNodeDto().getWidth());
			Node secondNode = findNode(segmentDto.getSecondNodeDto(), segmentDto.getSecondNodeDto().getLength(), segmentDto.getSecondNodeDto().getWidth());


			validate(segmentDto);
			Segment convertSegment = convertSegment(segmentDto, firstNode, secondNode);
			Segment save = segmentRepository.save(convertSegment);
			log.info("Save segment in database id: " + save.getSegId());
			segmentDto.setSegmentId(save.getSegId());
			return segmentDto;

		} catch (ApiException ae) {
			throw ae;
		} catch (Exception ex) {
			log.error("Internal error", ex);
			throw new ApiException(500, "Internal error");
		}
	}
	private Node findNode(NodeDto nodeDto, Double length, Double width){

		Node node = nodeRepository.findByLengthAndWidth(length, width);

		if (node == null) {
			log.info("Create  node - length: " + length + " width: " + width);
			node = convertNode(nodeDto);
			nodeRepository.save(node);
		}
		return node;
	}

	private Node convertNode(NodeDto nodeDto) {

		long time = Calendar.getInstance().getTime().getTime();
		Node node = new Node();
		node.setLength(nodeDto.getLength());
		node.setWidth(nodeDto.getWidth());
		node.setInsertDate(new Timestamp(time));
		node.setModifyDate(new Timestamp(time));
		return node;

	}

	private Segment convertSegment(SegmentDto segmentDto, Node firstNode, Node secondNode) {
		Date time = Calendar.getInstance().getTime();
		Segment segment = new Segment();
		segment.setNodeByFirNode(firstNode);
		segment.setNodeBySecNode(secondNode);
		segment.setName(segmentDto.getName());
		segment.setLength(segmentDto.getLength());
		segment.setInsertDate(new Timestamp(time.getTime()));
		segment.setModifyDate(new Timestamp(time.getTime()));
		return segment;
	}

	private void validate(SegmentDto segmentDto) {

		List<ApiErrorDetail> list = new ArrayList<>();

		if (segmentDto == null) {
			throw new ApiException(400, "Empty data");
		}

		if (StringUtils.isEmpty(segmentDto.getName())) {
			list.add(new ApiErrorDetail("Segment name is empty", new String[]{"name"}));
		}

		if (StringUtils.isEmpty(segmentDto.getLength())) {
			list.add(new ApiErrorDetail("Segment length is empty", new String[]{"length"}));
		}

		if (!list.isEmpty()) {
			throw new ApiException(400, "Validation errors", list);
		}

	}
}
