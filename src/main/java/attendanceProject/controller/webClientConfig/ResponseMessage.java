package attendanceProject.controller.webClientConfig;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Response message")
public class ResponseMessage {
    private String message;
}
