package hy.get.string.from.object.rma.services;

import hy.get.string.from.object.rma.dto.ApiErrorDetail;
import hy.get.string.from.object.rma.dto.NodeDto;
import hy.get.string.from.object.rma.dto.PolylineDto;
import hy.get.string.from.object.rma.dto.SegmentDto;
import hy.get.string.from.object.rma.entities.Node;
import hy.get.string.from.object.rma.entities.Segment;
import hy.get.string.from.object.rma.exceptions.ApiException;
import hy.get.string.from.object.rma.converters.SegmentConverters;
import hy.get.string.from.object.rma.repositories.NodeRepository;
import hy.get.string.from.object.rma.repositories.SegmentRepository;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import net.bedra.maciej.mblogging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SegmentService {

	private static Logger log = Logger.getLogger();

	private SegmentRepository segmentRepository;
	private NodeRepository nodeRepository;
	private NodeService nodeService;

	@Autowired
	public SegmentService(SegmentRepository segmentRepository, NodeRepository nodeRepository, NodeService nodeService) {
		this.segmentRepository = segmentRepository;
		this.nodeRepository = nodeRepository;
		this.nodeService = nodeService;
	}

	public SegmentDto getSingleSegment(Integer segmentId) {
		try {
			Optional<Segment> segmentById = segmentRepository.findById(segmentId);
			Segment segment = segmentById.orElse(null);

			return SegmentConverters.convertToDto(segment);
		} catch (ApiException ae) {
			throw ae;
		} catch (Exception ex) {
			log.error("Internal error", ex);
			throw new ApiException(500, "Internal error");
		}
	}

	public List<SegmentDto> getAllSegments() {
		try {
			Iterable<Segment> all = segmentRepository.findAll();
			Integer size = 0;

			if (all instanceof Collection) {
				size = ((Collection) all).size();
			}

			if (size == 0) {
				return null;
			}

			return StreamSupport.stream(all.spliterator(), false)
				.map(SegmentConverters::convertToDto)
				.collect(Collectors.toList());
		} catch (ApiException ae) {
			throw ae;
		} catch (Exception ex) {
			log.error("Internal error", ex);
			throw new ApiException(500, "Internal error");
		}
	}

	public SegmentDto createSegment(SegmentDto segmentDto) {
		try {
			Node firstNode = findNode(segmentDto.getFirstNode(), segmentDto.getFirstNode().getLat(), segmentDto.getFirstNode().getLng());
			Node secondNode = findNode(segmentDto.getSecondNode(), segmentDto.getSecondNode().getLat(), segmentDto.getSecondNode().getLng());
			validate(segmentDto);
			Segment convertSegment = convertSegment(segmentDto, firstNode, secondNode);
			Segment save = segmentRepository.save(convertSegment);
			log.info("Save segment in database id: " + save.getSegId());
			segmentDto.setSegId(save.getSegId());

			return segmentDto;
		} catch (ApiException ae) {
			throw ae;
		} catch (Exception ex) {
			log.error("Internal error", ex);
			throw new ApiException(500, "Internal error");
		}
	}

	private Node findNode(NodeDto nodeDto, Double lat, Double lng) {
		Node node = nodeRepository.findByLatAndLng(lat, lng);

		if (node == null) {
			log.info("Create  node - lat: " + lat + " lng: " + lng);
			node = convertNode(nodeDto);
			nodeRepository.save(node);
		}

		return node;
	}

	private Node convertNode(NodeDto nodeDto) {
		long time = Calendar.getInstance().getTime().getTime();
		Node node = new Node();
		node.setLng(nodeDto.getLng());
		node.setLat(nodeDto.getLat());
		node.setInsertDate(new Timestamp(time));
		node.setModifyDate(new Timestamp(time));

		return node;
	}

	private Segment convertSegment(SegmentDto segmentDto, Node firstNode, Node secondNode) {
		Date time = Calendar.getInstance().getTime();
		PolylineDto polyline = nodeService.getConvertedPolyline(
			firstNode.getLat().toString() + "," + firstNode.getLng(),
			secondNode.getLat().toString() + "," + secondNode.getLng()
		);
		Segment segment = new Segment();
		segment.setNodeByFirNode(firstNode);
		segment.setNodeBySecNode(secondNode);
		segment.setName(segmentDto.getName());
		segment.setLength(polyline.getLength());
		segment.setInsertDate(new Timestamp(time.getTime()));
		segment.setModifyDate(new Timestamp(time.getTime()));

		return segment;
	}

	private void validate(SegmentDto segmentDto) {
		List<ApiErrorDetail> list = new ArrayList<>();

		Segment findName = segmentRepository.findByName(segmentDto.getName());

		if (StringUtils.isEmpty(segmentDto.getName())) {
			list.add(new ApiErrorDetail("Segment name is empty", new String[]{"name"}));
		} else if (findName != null) {
			list.add(new ApiErrorDetail("Segment name is exists", new String[]{"name"}));
		}

		if (!list.isEmpty()) {
			throw new ApiException(400, "Validation errors", list);
		}
	}
}
