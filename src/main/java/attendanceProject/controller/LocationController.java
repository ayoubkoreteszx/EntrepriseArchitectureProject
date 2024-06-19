package attendanceProject.controller;

import attendanceProject.controller.Dto.location.CreateLocationParameters;
import attendanceProject.controller.Dto.location.LocationDTO;
import attendanceProject.domain.Location;
import attendanceProject.domain.LocationType;
import attendanceProject.service.locationService.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<List<LocationDTO>>(
                locationService.findAllLocations(),
                HttpStatus.OK
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        LocationDTO location = locationService.findLocationById(id);
        if (Objects.isNull(location)) {
            return new ResponseEntity<LocationType>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<LocationDTO>(location, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createLocation(@RequestBody CreateLocationParameters parameters) {
        return new ResponseEntity<LocationDTO>(
                locationService.createLocation(parameters),
                HttpStatus.CREATED
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateLocation(@PathVariable Long id, @RequestBody CreateLocationParameters parameters) {
        LocationDTO updated = locationService.updateLocation(id, parameters);
        if(updated == null)
            return new ResponseEntity<>("Record not exist", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<LocationDTO>(
                updated,
                HttpStatus.OK
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return new ResponseEntity<Location>(HttpStatus.NO_CONTENT);
    }
}
