package attendanceProject.controller.dto.locationType;

import attendanceProject.domain.LocationType;

import java.util.List;
import java.util.stream.Collectors;

public class LocationTypeMapper {
    public static LocationTypeResponse mapToResponse(LocationType locationType){
        if(locationType==null)
            return null;
        LocationTypeResponse response = new LocationTypeResponse();
        response.setType(locationType.getType());
        response.setId(locationType.getId());
        return response;
    }

    public static List<LocationTypeResponse> mapToResponse(List<LocationType> locationTypes){
        return locationTypes.stream().map(LocationTypeMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    public static LocationType mapToLocationType(LocationTypeRequest request){
        LocationType locationType = new LocationType();
        locationType.setType(request.getType());
        return locationType;
    }
}
