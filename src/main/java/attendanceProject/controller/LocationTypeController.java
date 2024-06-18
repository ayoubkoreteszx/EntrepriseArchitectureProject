package attendanceProject.controller;

import attendanceProject.domain.LocationType;
import attendanceProject.service.locationTypeService.LocationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/location-types")
public class LocationTypeController {
    @Autowired
    private LocationTypeService locationTypeService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<List<LocationType>>(
                locationTypeService.findAllLocationTypes(),
                HttpStatus.OK
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        LocationType locationType = locationTypeService.findLocationTypeById(id);
        if (Objects.isNull(locationType)) {
            return new ResponseEntity<LocationType>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<LocationType>(locationType, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createLocationType(@RequestBody LocationType locationType) {
        return new ResponseEntity<LocationType>(
                locationTypeService.createLocationType(locationType),
                HttpStatus.CREATED
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateLocationType(@PathVariable Long id, @RequestBody LocationType locationType) {
        return new ResponseEntity<LocationType>(
                locationTypeService.updateLocationType(id, locationType),
                HttpStatus.OK
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLocationType(@PathVariable Long id) {
        locationTypeService.deleteLocationType(id);
        return new ResponseEntity<LocationType>(HttpStatus.NO_CONTENT);
    }
}
