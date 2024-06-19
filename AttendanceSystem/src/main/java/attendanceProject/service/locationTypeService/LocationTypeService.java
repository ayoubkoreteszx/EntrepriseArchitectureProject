package attendanceProject.service.locationTypeService;

import attendanceProject.controller.dto.locationType.LocationTypeRequest;
import attendanceProject.controller.dto.locationType.LocationTypeResponse;
import attendanceProject.domain.LocationType;

import java.util.List;

public interface LocationTypeService {
    List<LocationTypeResponse> findAllLocationTypes();
    LocationTypeResponse createLocationType(LocationTypeRequest locationType);
    LocationTypeResponse findLocationTypeById(long id);
    LocationTypeResponse updateLocationType(Long id, LocationTypeRequest locationType);
    void deleteLocationType(Long id);
}
