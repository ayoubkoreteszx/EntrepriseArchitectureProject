package attendanceProject.service.locationService;

import attendanceProject.controller.dto.location.CreateLocationParameters;
import attendanceProject.controller.dto.location.LocationDTO;

import java.util.List;

public interface LocationService {
    public List<LocationDTO> findAllLocations();
    public LocationDTO findLocationById(long id);
    public LocationDTO createLocation(CreateLocationParameters parameters);
    public void deleteLocation(Long id);
    public LocationDTO updateLocation(Long id, CreateLocationParameters parameters);
}
