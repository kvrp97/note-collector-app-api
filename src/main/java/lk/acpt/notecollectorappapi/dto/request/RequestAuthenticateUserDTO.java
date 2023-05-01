package lk.acpt.notecollectorappapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestAuthenticateUserDTO {
    private String emailAddress;
    private String password;
}
