package attendanceProject.service.locationTypeService;

import attendanceProject.domain.LocationType;

import java.util.List;

public interface LocationTypeService {
    public List<LocationType> findAllLocationTypes();
    public LocationType findLocationTypeById(long id);
    public LocationType createLocationType(LocationType locationType);
    public void deleteLocationType(Long id);
    public LocationType updateLocationType(Long id, LocationType locationType);
}
