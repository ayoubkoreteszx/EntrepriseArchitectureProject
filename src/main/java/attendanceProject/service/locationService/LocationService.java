package attendanceProject.service.locationService;

import attendanceProject.controller.Dto.location.LocationDTO;

import java.util.List;

public interface LocationService {
    public List<LocationDTO> findAllLocations();
    public LocationDTO findLocationById(long id);
    public LocationDTO createLocation(LocationDTO locationDTO);
    public void deleteLocation(Long id);
    public LocationDTO updateLocation(Long id, LocationDTO locationDTO);
}
