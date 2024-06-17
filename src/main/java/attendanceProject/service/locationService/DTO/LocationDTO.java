package attendanceProject.service.locationService.DTO;

import lombok.Data;

@Data
public class LocationDTO {
    private long id;
    private String name;
    private int capacity;
    private long locationTypeId;
}
