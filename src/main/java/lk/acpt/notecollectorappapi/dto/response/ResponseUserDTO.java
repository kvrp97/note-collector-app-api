package lk.acpt.notecollectorappapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseUserDTO {
    private Integer userId;
    private String firstName;
}
