package attendanceProject.service.locationService;

import attendanceProject.controller.dto.location.CreateLocationParameters;
import attendanceProject.controller.dto.location.LocationDTO;
import attendanceProject.controller.dto.location.LocationDTOMapper;
import attendanceProject.domain.Location;
import attendanceProject.domain.LocationType;
import attendanceProject.repository.LocationRepository;
import attendanceProject.repository.LocationTypeRepository;
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
    LocationTypeRepository locationTypeRepository;
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
        LocationType locationType = locationTypeRepository.findById(parameters.getLocationTypeId()).orElse(null);
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
        LocationType locationType = locationTypeRepository.findById(parameters.getLocationTypeId()).orElse(null);
        Location location = locationRepository.findById(id).orElse(null);
        if(Objects.nonNull(locationType) && Objects.nonNull(location)){
            location = LocationDTOMapper.mapToLocation(parameters, locationType);
            location.setId(id);
            return LocationDTOMapper.mapToDTO(locationRepository.save(location));
        }
        return null;
    }
}
