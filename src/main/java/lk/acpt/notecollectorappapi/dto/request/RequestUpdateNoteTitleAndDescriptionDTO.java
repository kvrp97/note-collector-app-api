package lk.acpt.notecollectorappapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestUpdateNoteTitleAndDescriptionDTO {
    private Integer noteId;
    private String title;
    private String description;
    private String dateTime;
}
