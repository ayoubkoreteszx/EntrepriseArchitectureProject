package attendanceProject.service.locationTypeService;

import attendanceProject.controller.dto.locationType.LocationTypeMapper;
import attendanceProject.controller.dto.locationType.LocationTypeRequest;
import attendanceProject.controller.dto.locationType.LocationTypeResponse;
import attendanceProject.domain.LocationType;
import attendanceProject.repository.LocationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LocationTypeServiceImpl implements LocationTypeService {
    @Autowired
    private LocationTypeRepository locationTypeRepository;
    @Override
    public List<LocationTypeResponse> findAllLocationTypes() {
        return LocationTypeMapper.mapToResponse(locationTypeRepository.findAll());
    }

    @Override
    public LocationTypeResponse findLocationTypeById(long id) {
        return LocationTypeMapper.mapToResponse(locationTypeRepository.findById(id).orElse(null));
    }

    @Override
    public LocationTypeResponse createLocationType(LocationTypeRequest request) {
        LocationType locationType = LocationTypeMapper.mapToLocationType(request);
        return LocationTypeMapper.mapToResponse(locationTypeRepository.save(locationType));
    }

    @Override
    public void deleteLocationType(Long id) {
        locationTypeRepository.deleteById(id);
    }

    @Override
    public LocationTypeResponse updateLocationType(Long id, LocationTypeRequest request) {
        LocationType locationType = locationTypeRepository.findById(id).orElse(null);
        if(Objects.nonNull(locationType)) {
            locationType.setId(id);
            return LocationTypeMapper.mapToResponse(locationTypeRepository.save(locationType));
        }
        return null;
    }
}
