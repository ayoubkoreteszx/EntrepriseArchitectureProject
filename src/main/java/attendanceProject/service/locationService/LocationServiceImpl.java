package attendanceProject.service.locationService;

import attendanceProject.domain.Location;
import attendanceProject.domain.LocationType;
import attendanceProject.repository.LocationRepository;
import attendanceProject.service.locationTypeService.LocationTypeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    LocationTypeService locationTypeService;
    @Override
    public List<Location> findAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Location findLocationById(long id) {
        return locationRepository.findById(id).orElse(null);
    }

    @Override
    public Location createLocation(Location location, Long locationTypeId) {
        LocationType locationType = locationTypeService.findLocationTypeById(locationTypeId);
        if(Objects.isNull(locationType)) {
            throw new EntityNotFoundException("LocationType not found");
        }
        location.setLocationType(locationType);
        return locationRepository.save(location);
    }

    @Override
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

    @Override
    public Location updateLocation(Long id, Location location, Long locationTypeId) {
        LocationType locationType = locationTypeService.findLocationTypeById(locationTypeId);
        if(Objects.isNull(locationType)) {
            throw new EntityNotFoundException("LocationType not found");
        }
        location.setLocationType(locationType);
        location.setId(id);
        return locationRepository.save(location);
    }
}
