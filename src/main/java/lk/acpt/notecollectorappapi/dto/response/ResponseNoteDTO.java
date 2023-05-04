package lk.acpt.notecollectorappapi.dto.response;

import lk.acpt.notecollectorappapi.entity.NoteImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseNoteDTO {
    private Integer noteId;
    private String title;
    private String description;
    private String dateTime;
    private List<NoteImage> noteImages;
}
