package hy.get.string.from.object.rma.controllers;



import hy.get.string.from.object.rma.dto.VehicleDto;
import hy.get.string.from.object.rma.services.VehicleService;
import net.bedra.maciej.mblogging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class VehicleController {

	private static Logger log = Logger.getLogger();

	private VehicleService vehicleService;

	@Autowired
	public VehicleController(VehicleService vehicleService){
		this.vehicleService = vehicleService;
	}



	@RequestMapping(
		path = "/vehicle/{vehId}",
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public ResponseEntity<VehicleDto> getVehicleById(@PathVariable(name = "vehId") Integer vehId) {
		VehicleDto singleVehicle = vehicleService.getSingleVehicle(vehId);
		if(singleVehicle == null){
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.ok(singleVehicle);
		}
	}

	@RequestMapping(
		path = "/vehicle",
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public ResponseEntity<List<VehicleDto>> getVehicleById(){
		List<VehicleDto> vehicleDtoList = vehicleService.getAllVehicles();
		if(vehicleDtoList == null){
			return ResponseEntity.noContent().build();
		}else{
			return ResponseEntity.ok(vehicleDtoList);
		}
	}
	

	@RequestMapping(
		path = "/vehicle",
		method = RequestMethod.POST,
		consumes = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
		produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE
	)

	public ResponseEntity<VehicleDto> VehicleCreate(@RequestBody VehicleDto vehicleDto){
		log.info("Start create vehicle");

		VehicleDto newVehicleDto = vehicleService.createVehicle(vehicleDto);

		log.info("End create vehicle");
		return ResponseEntity.status(200).body(vehicleDto);
	}
}
