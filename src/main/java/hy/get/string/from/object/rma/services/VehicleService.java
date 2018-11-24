package hy.get.string.from.object.rma.services;

import hy.get.string.from.object.rma.dto.ApiError;
import hy.get.string.from.object.rma.dto.VehicleDto;
import hy.get.string.from.object.rma.entities.Vehicle;
import hy.get.string.from.object.rma.exceptions.ApiException;
import hy.get.string.from.object.rma.hy.get.string.from.object.rma.converters.VehicleConverters;
import hy.get.string.from.object.rma.repositories.VehicleRepository;
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

	private VehicleRepository vehicleRepository;

	@Autowired
	public VehicleService(VehicleRepository vehicleRepository){
		this.vehicleRepository = vehicleRepository;
	}


	public VehicleDto getSingleVehicle(Integer vehicleId){
		Optional<Vehicle> vehicleById = vehicleRepository.findById(vehicleId);
		Vehicle vehicle = vehicleById.orElse(null);

		return VehicleConverters.convertToDto(vehicle);
	}

	public VehicleDto createVehicle(VehicleDto vehicleDto){
		try{
			validate(vehicleDto);
			Vehicle convertVehicle = convertVehicle(vehicleDto);
			Vehicle save = vehicleRepository.save(convertVehicle);
			return vehicleDto;

		}catch (ApiException ae){
			ae.printStackTrace();
			throw ae;
		}catch (Exception e){
			e.printStackTrace();
			throw new ApiException(500,"Error internel");
		}
	}

	private Vehicle convertVehicle(VehicleDto vehicleDto) {
		Vehicle vehicle = new Vehicle();
		vehicle.setPlate(vehicleDto.getPlate());
		vehicle.setWeight(vehicleDto.getWeight());
		return vehicle;
	}

	private void validate(VehicleDto vehicleDto){
		List<ApiError> list = new ArrayList<>();
		if(vehicleDto == null){
			throw new ApiException(400,"empty data");
		}
		if(StringUtils.isEmpty(vehicleDto.getPlate())){

		}
		if(vehicleDto.getWeight() == null){

		} else if(vehicleDto.getWeight() < 0){

		}
		if(vehicleDto.getPlate() == null){

		}
		if(vehicleDto.getWeight() == null){

		}
		if(!list.isEmpty()){
			throw new ApiException(400, "Invalid create segment");
		}
	}

	public List<VehicleDto> getAllVehicles(){
		Iterable<Vehicle> all = vehicleRepository.findAll();
		Integer size = 0;
		if(all instanceof Collection){
			size = ((Collection)all).size();
		}
		if(size == 0)
			return null;
		return StreamSupport.stream(all.spliterator(),false)
			.map(p -> VehicleConverters.convertToDto(p))
			.collect(Collectors.toList());
	}


}
