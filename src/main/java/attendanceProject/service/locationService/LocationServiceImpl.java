package attendanceProject.service.locationService;

import attendanceProject.domain.Location;
import attendanceProject.domain.LocationType;
import attendanceProject.repository.LocationRepository;
import attendanceProject.service.locationService.DTO.LocationDTO;
import attendanceProject.service.locationService.DTO.LocationDTOMapper;
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
    public List<LocationDTO> findAllLocations() {
        return LocationDTOMapper.mapToDTOList(locationRepository.findAll());
    }

    @Override
    public LocationDTO findLocationById(long id) {
        return LocationDTOMapper.mapToDTO(locationRepository.findById(id).orElse(null));
    }

    @Override
    public LocationDTO createLocation(LocationDTO locationDTO) {
        LocationType locationType = locationTypeService.findLocationTypeById(locationDTO.getLocationTypeId());
        if(Objects.isNull(locationType)) {
            throw new EntityNotFoundException("LocationType not found");
        }
        Location location = new Location();
        location.setLocationType(locationType);
        location.setName(locationDTO.getName());
        location.setCapacity(locationDTO.getCapacity());
        return LocationDTOMapper.mapToDTO(locationRepository.save(location));
    }

    @Override
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

    @Override
    public LocationDTO updateLocation(Long id, LocationDTO locationDTO) {
        LocationType locationType = locationTypeService.findLocationTypeById(locationDTO.getLocationTypeId());
        Location location = locationRepository.findById(id).orElse(null);
        if(Objects.nonNull(locationType) && Objects.nonNull(location)){
            location.setLocationType(locationType);
            location.setName(locationDTO.getName());
            location.setCapacity(locationDTO.getCapacity());
            locationRepository.save(location);
        }

        return LocationDTOMapper.mapToDTO(location);
    }
}
