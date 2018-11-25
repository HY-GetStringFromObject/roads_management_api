package hy.get.string.from.object.rma.services;

import hy.get.string.from.object.rma.dto.ApiErrorDetail;
import hy.get.string.from.object.rma.dto.NodeDto;
import hy.get.string.from.object.rma.entities.Node;
import hy.get.string.from.object.rma.exceptions.ApiException;
import hy.get.string.from.object.rma.hy.get.string.from.object.rma.converters.NodeConverters;
import hy.get.string.from.object.rma.repositories.NodeRepository;
import net.bedra.maciej.mblogging.Logger;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class NodeService {

	private static Logger log = Logger.getLogger();

	private NodeRepository nodeRepository;

	public NodeService(NodeRepository nodeRepository) {
		this.nodeRepository = nodeRepository;
	}

	public NodeDto createNode(NodeDto nodeDto) {

		try {
			List<ApiErrorDetail> list = new ArrayList<>();

			if (nodeDto.getLng() == null || nodeDto.getLng() < 0) {
				list.add(new ApiErrorDetail("lng is empty or it's too small", new String[]{"lng"}));
			}

			if (nodeDto.getLag() == null || nodeDto.getLag() < 0) {
				list.add(new ApiErrorDetail("lag is empty or it's too small", new String[]{"lag"}));
			}

			if (!list.isEmpty()) {
				throw new ApiException(400, "Validation errors", list);
			}

			Node findNode = nodeRepository.findByLatAndLng(nodeDto.getLag(), nodeDto.getLng());

			if (findNode == null) {

				findNode = convertToDbNode(nodeDto);
			} else {

				findNode.setModifyDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
			}
			Node save = nodeRepository.save(findNode);
			log.info("Save node to DB id: " + save.getNodId());
			return NodeConverters.convertToDto(save);

		} catch (ApiException ae) {
			throw ae;
		} catch (Exception ex) {
			log.error("Internal error", ex);
			throw new ApiException(500, "Internal error");
		}


	}

	private Node convertToDbNode(NodeDto nodeDto) {

		Date time = Calendar.getInstance().getTime();
		Node node = new Node();
		node.setLat(nodeDto.getLag());
		node.setLng(nodeDto.getLng());
		node.setInsertDate(new Timestamp(time.getTime()));
		node.setModifyDate(new Timestamp(time.getTime()));

		return node;
	}
}
