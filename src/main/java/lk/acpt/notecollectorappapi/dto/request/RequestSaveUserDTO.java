package lk.acpt.notecollectorappapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestSaveUserDTO {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
}
