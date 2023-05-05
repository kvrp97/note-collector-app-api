package lk.acpt.notecollectorappapi.service;

import lk.acpt.notecollectorappapi.dto.request.RequestNoteImageRemoveDTO;
import lk.acpt.notecollectorappapi.entity.Note;
import lk.acpt.notecollectorappapi.exception.NoteUpdateException;

public interface NoteImageService {
    Note updateNoteByRemovingImages(RequestNoteImageRemoveDTO noteImageRemoveDTO) throws NoteUpdateException;

}
