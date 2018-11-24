package hy.get.string.from.object.rma.services;

import hy.get.string.from.object.rma.dto.NodeDto;
import hy.get.string.from.object.rma.dto.SegmentDto;
import hy.get.string.from.object.rma.entities.Node;
import hy.get.string.from.object.rma.entities.Segment;
import hy.get.string.from.object.rma.repositories.SegmentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SegmentService {

	private SegmentRepository segmentRepository;

	public SegmentService(SegmentRepository segmentRepository) {
		this.segmentRepository = segmentRepository;
	}

	public SegmentDto getSingleSegment(Integer segmentId) {

		Optional<Segment> segmentById = segmentRepository.findById(segmentId);
		Segment segment = segmentById.orElse(null);

		return convert(segment);
	}

	public List<SegmentDto> getAllSegments() {
		Iterable<Segment> all = segmentRepository.findAll();
		Integer size = 0;
		if(all instanceof Collection) {
			size = ((Collection)all).size();
		}
		if(size == 0) {
			return null;
		}

		return StreamSupport.stream(all.spliterator(), false)
			.map(this::convert)
			.collect(Collectors.toList());
	}

	private SegmentDto convert(Segment p) {
		if (p == null) {
			return null;
		}

		return SegmentDto.builder()
			.firstNodeDto(convert(p.getNodeByFirNode()))
			.secondNodeDto(convert(p.getNodeBySecNode()))
			.length(p.getLength())
			.name(p.getName())
			.build();
	}

	private NodeDto convert(Node n) {
		if(n == null)  {
			return null;
		}

		return NodeDto.builder()
			.length(n.getLength())
			.width(n.getWidth())
			.build();
	}
}
