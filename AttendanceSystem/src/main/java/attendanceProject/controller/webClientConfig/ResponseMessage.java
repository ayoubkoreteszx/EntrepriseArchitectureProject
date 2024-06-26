package attendanceProject.controller.webClientConfig;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Response message")
public class ResponseMessage {
    private String message;
    public ResponseMessage() {}
}
