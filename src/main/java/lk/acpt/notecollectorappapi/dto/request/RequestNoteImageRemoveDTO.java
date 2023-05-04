package lk.acpt.notecollectorappapi.dto.request;

import lk.acpt.notecollectorappapi.entity.NoteImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestNoteImageRemoveDTO {
    private Integer noteId;
    private String dateTime;
    private List<NoteImage> noteImageList;
}
