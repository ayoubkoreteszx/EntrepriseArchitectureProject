package attendanceProject.controller;

import attendanceProject.controller.dto.locationType.LocationTypeRequest;
import attendanceProject.controller.dto.locationType.LocationTypeResponse;
import attendanceProject.domain.LocationType;
import attendanceProject.service.locationTypeService.LocationTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/location-types")
public class LocationTypeController {

    private LocationTypeService locationTypeService;

    public LocationTypeController(LocationTypeService locationTypeService) {
        this.locationTypeService = locationTypeService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<List<LocationTypeResponse>>(
                locationTypeService.findAllLocationTypes(),
                HttpStatus.OK
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        LocationTypeResponse locationType = locationTypeService.findLocationTypeById(id);
        if (Objects.isNull(locationType)) {
            return new ResponseEntity<LocationTypeResponse>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<LocationTypeResponse>(locationType, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createLocationType(@RequestBody LocationTypeRequest locationType) {
        return new ResponseEntity<LocationTypeResponse>(
                locationTypeService.createLocationType(locationType),
                HttpStatus.CREATED
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateLocationType(@PathVariable Long id, @RequestBody LocationTypeRequest locationType) {
        return new ResponseEntity<LocationTypeResponse>(
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
