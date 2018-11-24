package hy.get.string.from.object.rma.services;

import hy.get.string.from.object.rma.dto.ApiErrorDetail;
import hy.get.string.from.object.rma.dto.SegmentLimitationDto;
import hy.get.string.from.object.rma.entities.Limitation;
import hy.get.string.from.object.rma.entities.Segment;
import hy.get.string.from.object.rma.entities.SegmentLimitation;
import hy.get.string.from.object.rma.exceptions.ApiException;
import hy.get.string.from.object.rma.repositories.LimitationRepository;
import hy.get.string.from.object.rma.repositories.SegmentLimitationRepository;
import hy.get.string.from.object.rma.repositories.SegmentRepository;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.bedra.maciej.mblogging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SegmentLimitationService {

	private static Logger log = Logger.getLogger();

	private SegmentRepository segmentRepository;
	private LimitationRepository limitationRepository;
	private SegmentLimitationRepository segmentLimitationRepository;

	@Autowired
	public SegmentLimitationService(SegmentRepository segmentRepository, LimitationRepository limitationRepository, SegmentLimitationRepository segmentLimitationRepository) {
		this.segmentRepository = segmentRepository;
		this.limitationRepository = limitationRepository;
		this.segmentLimitationRepository = segmentLimitationRepository;
	}

	public SegmentLimitationDto createSegmentLimitation(Integer segId, Integer limId) {
		try {
			List<ApiErrorDetail> errors = new ArrayList<>();
			Segment segment = segmentRepository.findBySegId(segId);

			if (segment == null) {
				errors.add(new ApiErrorDetail("Segment do not exists", new String[]{"segId"}));
			}

			Limitation limitation = limitationRepository.findByLimId(limId);

			if (limitation == null) {
				errors.add(new ApiErrorDetail("Limitation do not exists", new String[]{"limId"}));
			}

			if (!errors.isEmpty()) {
				SegmentLimitation sl = new SegmentLimitation();
				sl.setSegmentBySegSegId(segment);
				sl.setLimitationByLimLimId(limitation);
				Date now = Calendar.getInstance().getTime();
				Timestamp stamp = new Timestamp(now.getTime());
				sl.setModifyDate(stamp);
				sl.setInsertDate(stamp);
				segmentLimitationRepository.save(sl);

				return convertToSegmentLimitationDto(segId, limId);
			} else {
				throw new ApiException(400, "Validation errors", errors);
			}
		} catch (ApiException ae) {
			throw ae;
		} catch (Exception ex) {
			log.error("Internal error", ex);
			throw new ApiException(500, "Internal error");
		}
	}

	private SegmentLimitationDto convertToSegmentLimitationDto(Integer segId, Integer limId) {
		return SegmentLimitationDto.builder()
			.segSegId(segId)
			.limLimId(limId)
			.build();
	}

}
