package attendanceProject.controller.Dto.location;

import attendanceProject.domain.Location;

import java.util.List;
import java.util.stream.Collectors;

public class LocationDTOMapper {
    public static LocationDTO mapToDTO(Location location) {
        if (location == null) {
            return null;
        }
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(location.getId());
        locationDTO.setName(location.getName());
        locationDTO.setCapacity(location.getCapacity());
        locationDTO.setLocationTypeId(location.getLocationType().getId());
        return locationDTO;
    }
    public static List<LocationDTO> mapToDTOList(List<Location> locations) {
        return locations.stream().map(LocationDTOMapper::mapToDTO)
                .collect(Collectors.toList());
    }
}
