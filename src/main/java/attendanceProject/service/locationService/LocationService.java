package attendanceProject.service.locationService;

import attendanceProject.domain.Location;
import attendanceProject.domain.LocationType;

import java.util.List;

public interface LocationService {
    public List<Location> findAllLocations();
    public Location findLocationById(long id);
    public Location createLocation(Location location, Long locationTypeId);
    public void deleteLocation(Long id);
    public Location updateLocation(Long id, Location location, Long locationTypeId);
}
