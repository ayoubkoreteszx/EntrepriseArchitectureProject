package attendanceProject.controller.dto.location;

import lombok.Data;

@Data
public class CreateLocationParameters {
    private long id;
    private String name;
    private int capacity;
    private long locationTypeId;
}
