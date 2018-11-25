package hy.get.string.from.object.rma.services;

import hy.get.string.from.object.rma.dto.ApiErrorDetail;
import hy.get.string.from.object.rma.dto.LimitationDto;
import hy.get.string.from.object.rma.dto.ApiError;
import hy.get.string.from.object.rma.dto.NodeDto;
import hy.get.string.from.object.rma.entities.Limitation;
import hy.get.string.from.object.rma.dto.PolylineDto;
import hy.get.string.from.object.rma.entities.Node;
import hy.get.string.from.object.rma.entities.Segment;
import hy.get.string.from.object.rma.entities.SegmentLimitation;
import hy.get.string.from.object.rma.exceptions.ApiException;
import hy.get.string.from.object.rma.repositories.LimitationRepository;
import hy.get.string.from.object.rma.repositories.NodeRepository;
import hy.get.string.from.object.rma.repositories.SegmentLimitationRepository;
import hy.get.string.from.object.rma.repositories.SegmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RouteService {

	SegmentLimitationRepository segmentLimitationRepository;
	LimitationRepository limitationRepository;
	SegmentRepository segmentRepository;
	NodeRepository nodeRepository;

	private NodeService nodeService;

	public RouteService(NodeService nodeService, SegmentLimitationRepository segmentLimitationRepository, LimitationRepository limitationRepository, SegmentRepository segmentRepository, NodeRepository nodeRepository) {
		this.segmentLimitationRepository = segmentLimitationRepository;
		this.limitationRepository = limitationRepository;
		this.segmentRepository = segmentRepository;

		this.nodeRepository = nodeRepository;
		this.nodeService = nodeService;
	}
	
	public PolylineDto getBestRoute(Integer startNodId, Integer endNodId) {
		try {

			List<ApiErrorDetail> errorList = new ArrayList<>();
			Node firstNode = nodeRepository.findByNodId(startNodId);
			Node secondNode = nodeRepository.findByNodId(endNodId);

			if (firstNode == null) {
				errorList.add(new ApiErrorDetail("Start node do not existe", new String[]{"startNodId"}));
			}

			if (secondNode == null) {
				errorList.add(new ApiErrorDetail("End node do not existe", new String[]{"endNodId"}));
			}

			if (!errorList.isEmpty()) {
				throw new ApiException(400, "Validation error", errorList);
			} else {

				PolylineDto convertedPolyline = nodeService.getConvertedPolyline(firstNode.getLat().toString() + "," + firstNode.getLng().toString(), secondNode.getLat().toString() + "," + secondNode.getLng().toString());
				return convertedPolyline;

			}
		} catch (ApiException ae) {
			throw ae;
		} catch (Exception ex) {
			log.error("Internal error", ex);
			throw new ApiException(500, "Internal error");
		}
	}

	public Map<Integer, Segment> getRoute(NodeDto startNodeDto, NodeDto endNodeDto, List<LimitationDto> limitationIdList) {
//		List<Limitation> mustHaveLim =  new ArrayList<>();
//		if(limitationIdList != null) {
//			mustHaveLim = limitationIdList.stream()
//				.map(p -> limitationRepository.findByLimId(p.getLimId()))
//				.collect(Collectors.toList());
//		}

		Node endNode = nodeRepository.findByNodId(endNodeDto.getNodId());
		Node startNode = nodeRepository.findByNodId(startNodeDto.getNodId());
		List<Segment> segmentWithStartNode = segmentRepository.findByNodeByFirNode(startNode);

		List<Segment> segmentWithEndNode = segmentRepository.findByNodeBySecNode(endNode);
		List<Segment> okStartSegments = findAllOkSegments(segmentWithEndNode, null);
		List<Segment> okEndSegments = findAllOkSegments(segmentWithStartNode, null);
		Map<Segment, Map<Integer,Segment>> routeMap = new HashMap<>();



		okStartSegments.forEach(p -> {
			Map<Integer, Segment> routeSecondMap = new HashMap<>();
			AtomicInteger i = new AtomicInteger();
			boolean route = findRoute(p, okEndSegments, null, routeSecondMap, i);
			if(route) {
				routeSecondMap.put(0, p);
				routeMap.put(p, routeSecondMap);
			}
		});


		for (Segment p: routeMap.keySet()) {
			if(!routeMap.get(p).isEmpty()) {
				return routeMap.get(p);
			}
		}
		return null;
	}

	public boolean findRoute(Segment fromSegment, List<Segment> toSegmentList, List<Limitation> mustHaveLimList,
				 Map<Integer, Segment> route, AtomicInteger i) {
		i.getAndIncrement();
		List<Segment> okList = segmentRepository.findByNodeBySecNode(fromSegment.getNodeByFirNode());
		List<Segment> allOkSegments = findAllOkSegments(okList, mustHaveLimList);
		if (allOkSegments != null && !allOkSegments.isEmpty()) {
			List<Segment> collect = allOkSegments.stream().filter(p -> toSegmentList.contains(p)).collect(Collectors.toList());
			if (!collect.isEmpty()) {
				route.put(i.get(), collect.stream().findFirst().orElseGet(null));
				return true;
			} else {
				//Tutaj pewnie by sie chujowo zachowalo gdyby bylo wiecej segmentow do wyboru.
				allOkSegments.forEach(p -> {
					boolean isOkRoute = findRoute(p, toSegmentList, mustHaveLimList, route, i);
					if (isOkRoute) {
						route.put(i.get(), p);
					}
				});
			}
		}
		return false;
	}



	private List<Segment> findAllOkSegments(List<Segment> segmentList, List<Limitation> mustHaveLim) {
		if(mustHaveLim == null || mustHaveLim.isEmpty()) {
			return segmentList;
		}
		List<Segment> collect = segmentList.stream().filter(p -> isLimitationOk(p, mustHaveLim)).collect(Collectors.toList());
		return collect;
	}

	private boolean isLimitationOk(Segment checkingSeg, List<Limitation> musHaveLim) {
		List<SegmentLimitation> bySegmentBySegSegId = segmentLimitationRepository.findBySegmentBySegSegId(checkingSeg);
		return bySegmentBySegSegId.containsAll(musHaveLim);
	}
}
