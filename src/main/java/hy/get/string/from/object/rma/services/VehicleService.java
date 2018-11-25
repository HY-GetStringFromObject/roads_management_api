package hy.get.string.from.object.rma.services;

import hy.get.string.from.object.rma.dto.ApiErrorDetail;
import hy.get.string.from.object.rma.dto.VehicleDto;
import hy.get.string.from.object.rma.entities.Vehicle;
import hy.get.string.from.object.rma.exceptions.ApiException;
import hy.get.string.from.object.rma.converters.VehicleConverters;
import hy.get.string.from.object.rma.repositories.VehicleRepository;
import net.bedra.maciej.mblogging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VehicleService {

	private static Logger log = Logger.getLogger();

	private VehicleRepository vehicleRepository;

	@Autowired
	public VehicleService(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}

	public VehicleDto getSingleVehicle(Integer vehicleId) {
		try {
			Optional<Vehicle> vehicleById = vehicleRepository.findById(vehicleId);
			Vehicle vehicle = vehicleById.orElse(null);

			return VehicleConverters.convertToDto(vehicle);
		} catch (ApiException ae) {
			throw ae;
		} catch (Exception ex) {
			log.error("Internal error", ex);
			throw new ApiException(500, "Internal error");
		}
	}

	public VehicleDto createVehicle(VehicleDto vehicleDto) {
		try {
			validate(vehicleDto);
			Vehicle convertVehicle = convertVehicle(vehicleDto);
			vehicleRepository.save(convertVehicle);

			return vehicleDto;
		} catch (ApiException ae) {
			throw ae;
		} catch (Exception e) {
			log.error("Internal error", e);
			throw new ApiException(500, "Internal error");
		}
	}

	private Vehicle convertVehicle(VehicleDto vehicleDto) {
		Vehicle vehicle = new Vehicle();
		vehicle.setPlate(vehicleDto.getPlate());

		return vehicle;
	}

	private void validate(VehicleDto vehicleDto) {
		List<ApiErrorDetail> list = new ArrayList<>();

		if (vehicleDto == null) {
			throw new ApiException(400, "empty data");
		}

		if (StringUtils.isEmpty(vehicleDto.getPlate())) {
			list.add(new ApiErrorDetail("Vehicle plate is empty", new String[]{"plate"}));
		}

		if (vehicleDto.getWeight() == null) {
			list.add(new ApiErrorDetail("Vehicle weight is empty", new String[]{"weight"}));
		} else if (vehicleDto.getWeight() < 0) {
			list.add(new ApiErrorDetail("Vehicle weight lower than zero", new String[]{"weight"}));
		}

		if (!list.isEmpty()) {
			throw new ApiException(400, "Invalid create segment", list);
		}
	}

	public List<VehicleDto> getAllVehicles() {
		try {
			Iterable<Vehicle> all = vehicleRepository.findAll();
			Integer size = 0;

			if (all instanceof Collection) {
				size = ((Collection) all).size();
			}

			if (size == 0)
				return null;

			return StreamSupport.stream(all.spliterator(), false)
				.map(VehicleConverters::convertToDto)
				.collect(Collectors.toList());
		} catch (ApiException ae) {
			throw ae;
		} catch (Exception ex) {
			log.error("Internal error", ex);
			throw new ApiException(500, "Internal error");
		}
	}

}
