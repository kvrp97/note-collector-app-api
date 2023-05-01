package lk.acpt.notecollectorappapi.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandardResponse {
    private Integer code;
    private String message;
    private Object data;
}
