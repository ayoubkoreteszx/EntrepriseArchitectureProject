package attendanceProject.service.locationService;

import attendanceProject.domain.Location;
import attendanceProject.domain.LocationType;
import attendanceProject.service.locationService.DTO.LocationDTO;

import java.util.List;

public interface LocationService {
    public List<LocationDTO> findAllLocations();
    public LocationDTO findLocationById(long id);
    public LocationDTO createLocation(LocationDTO locationDTO);
    public void deleteLocation(Long id);
    public LocationDTO updateLocation(Long id, LocationDTO locationDTO);
}
