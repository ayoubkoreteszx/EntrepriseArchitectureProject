package attendanceProject.service.locationTypeService;

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
    public List<LocationType> findAllLocationTypes() {
        return locationTypeRepository.findAll();
    }

    @Override
    public LocationType findLocationTypeById(long id) {
        return locationTypeRepository.findById(id).orElse(null);
    }

    @Override
    public LocationType createLocationType(LocationType locationType) {
        return locationTypeRepository.save(locationType);
    }

    @Override
    public void deleteLocationType(Long id) {
        locationTypeRepository.deleteById(id);
    }

    @Override
    public LocationType updateLocationType(Long id, LocationType locationType) {
        LocationType oldLocationType = findLocationTypeById(id);
        if(Objects.nonNull(oldLocationType)) {
            locationType.setId(id);
            locationTypeRepository.save(locationType);
        }
        return locationType;
    }
}
