package EAProjectEmail.EAProjectEmail.Email;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    private String to;
    private String subject;
    private String text;
}
