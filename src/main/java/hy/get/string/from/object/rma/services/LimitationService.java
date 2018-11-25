package hy.get.string.from.object.rma.services;

import hy.get.string.from.object.rma.dto.LimitationDto;
import hy.get.string.from.object.rma.entities.Limitation;
import hy.get.string.from.object.rma.exceptions.ApiException;
import hy.get.string.from.object.rma.hy.get.string.from.object.rma.converters.LimitationConverters;
import hy.get.string.from.object.rma.repositories.LimitationRepository;
import net.bedra.maciej.mblogging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LimitationService {

	private static Logger log = Logger.getLogger();

	private LimitationRepository limitationRepository;

	@Autowired
	public LimitationService(LimitationRepository limitationRepository) {
		this.limitationRepository = limitationRepository;
	}

	public LimitationDto getLimitationById(Integer limId) {
		try {
			Limitation lim = limitationRepository.findByLimId(limId);

			if (lim == null) {
				return null;
			}

			return LimitationConverters.convertToDto(lim);
		} catch (ApiException ae) {
			throw ae;
		} catch (Exception ex) {
			log.error("Internal error", ex);
			throw new ApiException(500, "Internal error");
		}
	}

	public List<LimitationDto> getAllLimitations() {
		try {
			Iterable<Limitation> all = limitationRepository.findAll();
			int size = 0;

			if (all instanceof Collection) {
				size = ((Collection) all).size();
			}

			if (size == 0) {
				return null;
			}

			return StreamSupport.stream(all.spliterator(), false)
				.map(LimitationConverters::convertToDto)
				.collect(Collectors.toList());
		} catch (ApiException ae) {
			throw ae;
		} catch (Exception ex) {
			log.error("Internal error", ex);
			throw new ApiException(500, "Internal error");
		}
	}

}
