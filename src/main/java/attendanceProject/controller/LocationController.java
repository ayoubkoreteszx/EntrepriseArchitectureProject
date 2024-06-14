package attendanceProject.controller;

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
@RequestMapping("/sys-admin/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<List<Location>>(
                locationService.findAllLocations(),
                HttpStatus.OK
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Location location = locationService.findLocationById(id);
        if (Objects.isNull(location)) {
            return new ResponseEntity<LocationType>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Location>(location, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createLocation(@RequestBody Location location, @RequestParam Long locationTypeId) {
        return new ResponseEntity<Location>(
                locationService.createLocation(location, locationTypeId),
                HttpStatus.CREATED
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateLocation(@PathVariable Long id, @RequestBody Location location, @RequestParam Long locationTypeId) {
        return new ResponseEntity<Location>(
                locationService.updateLocation(id, location, locationTypeId),
                HttpStatus.OK
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return new ResponseEntity<Location>(HttpStatus.NO_CONTENT);
    }
}
