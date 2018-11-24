package hy.get.string.from.object.rma.services;

import hy.get.string.from.object.rma.dto.ApiError;
import hy.get.string.from.object.rma.dto.ApiErrorDetail;
import hy.get.string.from.object.rma.dto.NodeDto;
import hy.get.string.from.object.rma.dto.SegmentDto;
import hy.get.string.from.object.rma.entities.Node;
import hy.get.string.from.object.rma.entities.Segment;
import hy.get.string.from.object.rma.exceptions.ApiException;
import hy.get.string.from.object.rma.repositories.NodeRepository;
import hy.get.string.from.object.rma.repositories.SegmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class SegmentService {

	private SegmentRepository segmentRepository;
	private NodeRepository nodeRepository;

	@Autowired
	public SegmentService(SegmentRepository segmentRepository, NodeRepository nodeRepository) {
		this.segmentRepository = segmentRepository;
		this.nodeRepository = nodeRepository;
	}

	public SegmentDto createSegment(SegmentDto segmentDto) {

		try {
//			segmentDto.getFirstNodeDto()


			Node firstNode = nodeRepository.findByLengthAndWidth(segmentDto.getFirstNodeDto().getLength(), segmentDto.getFirstNodeDto().getWidth());

			if(firstNode == null){
				NodeDto nodeDto = convertNode(firstNode);

//				nodeRepository.save();
			}

			Node secondNode = nodeRepository.findByLengthAndWidth(segmentDto.getSecondNodeDto().getLength(), segmentDto.getSecondNodeDto().getWidth());

			if(secondNode == null){

				convertNode(secondNode);
			}

			validate(segmentDto);
			Segment convertSegment = convertSegment(segmentDto);


			Segment save = segmentRepository.save(convertSegment);
			return null;

		} catch (ApiException ae) {

			ae.printStackTrace();
			throw ae;


		} catch (Exception e) {

			e.printStackTrace();
			throw new ApiException(500, "Error internel");


		}
	}

	private NodeDto convertNode(Node node){

		NodeDto.NodeDtoBuilder nodeDtoBuilder = NodeDto.builder();
		nodeDtoBuilder.length(node.getLength());
		nodeDtoBuilder.width(node.getWidth());

		return nodeDtoBuilder.build();

	}

	private Segment convertSegment(SegmentDto segmentDto) {
		Segment segment = new Segment();
//		segment.setNodeByFirNode(segmentDto.getFirstNodeDto());
//		segment.firstNodeDto(segmentDto.getFirstNodeDto());
//		segment.secondNodeDto(segmentDto.getSecondNodeDto());
		segment.setName(segmentDto.getName());
		segment.setLength(segmentDto.getLength());
		return segment;
	}

	private void validate(SegmentDto segmentDto) {


		List<ApiError> list = new ArrayList<>();
		if (segmentDto == null) {
			throw new ApiException(400, "empty data");
		}

		if (StringUtils.isEmpty(segmentDto.getName())) {

		}

		if (StringUtils.isEmpty(segmentDto.getLength())) {

		}

		if (segmentDto.getFirstNodeDto() != null) {

		}

		if (segmentDto.getSecondNodeDto() != null) {

		}

		if (!list.isEmpty()) {
			throw new ApiException(400, "Invalid create segment");
		}


	}
}
