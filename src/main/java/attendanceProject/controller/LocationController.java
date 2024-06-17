package attendanceProject.controller;

import attendanceProject.domain.Location;
import attendanceProject.domain.LocationType;
import attendanceProject.service.locationService.DTO.LocationDTO;
import attendanceProject.service.locationService.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/sys-admin/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;

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
    public ResponseEntity<?> createLocation(@RequestBody LocationDTO location) {
        return new ResponseEntity<LocationDTO>(
                locationService.createLocation(location),
                HttpStatus.CREATED
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateLocation(@PathVariable Long id, @RequestBody LocationDTO location) {
        return new ResponseEntity<LocationDTO>(
                locationService.updateLocation(id, location),
                HttpStatus.OK
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return new ResponseEntity<Location>(HttpStatus.NO_CONTENT);
    }
}
