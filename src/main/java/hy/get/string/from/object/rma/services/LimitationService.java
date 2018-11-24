package hy.get.string.from.object.rma.services;

import hy.get.string.from.object.rma.dto.LimitationDto;
import hy.get.string.from.object.rma.entities.Limitation;
import hy.get.string.from.object.rma.hy.get.string.from.object.rma.converters.LimitationConverters;
import hy.get.string.from.object.rma.hy.get.string.from.object.rma.converters.SegmentConverters;
import hy.get.string.from.object.rma.repositories.LimitationRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LimitationService {

	private LimitationRepository limitationRepository;

	public LimitationService(LimitationRepository limitationRepository) {
		this.limitationRepository = limitationRepository;
	}

	public LimitationDto getLimitationById(Integer limId) {
		Limitation lim = limitationRepository.findByLimId(limId);
		if(lim == null) {
			return null;
		}

		return LimitationConverters.convertToDto(lim);
	}

	public List<LimitationDto> getAllLimitation() {
		Iterable<Limitation> all = limitationRepository.findAll();
		int size = 0;
		if(all instanceof Collection) {
			size = ((Collection)all).size();
		}
		if(size == 0) {
			return null;
		}
		return StreamSupport.stream(all.spliterator(), false)
			.map(p ->LimitationConverters.convertToDto(p))
			.collect(Collectors.toList());
	}

}
