package btl.web.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import btl.Car;
import btl.Employee;
import btl.Pair;
import btl.Trip;
import btl.data.CarRepository;
import btl.data.EmployeeRepository;
import btl.data.TripRepository;
import lombok.extern.java.Log;

@RestController
@RequestMapping(path = "/trip", produces = "application/json")
@CrossOrigin(origins = "*") 
public class TripController {
	private final TripRepository tripRepo;
	
	@Autowired
	EntityLinks entityLinks;
	public TripController(TripRepository tripRepo) {
		this.tripRepo = tripRepo;
	}
	
	@GetMapping("/drive/{id}")
	public Iterable<Trip> getDriverById(@PathVariable("id") Long id) {
		List<Trip> trip = new ArrayList<>();
		trip.addAll(tripRepo.findByDriverId(id));
		trip.addAll(tripRepo.findBySubdriverId(id));
		return trip;
	}

	
	@GetMapping("/car/{id}")
	public Iterable<Trip> showCarForm(@PathVariable("id") Long id) {
		return tripRepo.findByCarId(id); 
	}
	
}
