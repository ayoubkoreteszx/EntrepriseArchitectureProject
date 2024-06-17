package attendanceProject.service.locationService;

import attendanceProject.controller.Dto.location.CreateLocationParameters;
import attendanceProject.domain.Location;
import attendanceProject.domain.LocationType;
import attendanceProject.repository.LocationRepository;
import attendanceProject.controller.Dto.location.LocationDTO;
import attendanceProject.controller.Dto.location.LocationDTOMapper;
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
    public LocationDTO createLocation(CreateLocationParameters parameters) {
        LocationType locationType = locationTypeService.findLocationTypeById(parameters.getLocationTypeId());
        if(Objects.isNull(locationType)) {
            throw new EntityNotFoundException("LocationType not found");
        }
        Location location = LocationDTOMapper.mapToLocation(parameters, locationType);
        return LocationDTOMapper.mapToDTO(locationRepository.save(location));
    }

    @Override
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

    @Override
    public LocationDTO updateLocation(Long id, CreateLocationParameters parameters) {
        LocationType locationType = locationTypeService.findLocationTypeById(parameters.getLocationTypeId());
        Location location = locationRepository.findById(id).orElse(null);
        if(Objects.nonNull(locationType) && Objects.nonNull(location)){
            location = LocationDTOMapper.mapToLocation(parameters, locationType);
            location.setId(id);
            return LocationDTOMapper.mapToDTO(locationRepository.save(location));
        }
        return null;
    }
}
