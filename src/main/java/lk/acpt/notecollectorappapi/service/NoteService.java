package lk.acpt.notecollectorappapi.service;

import lk.acpt.notecollectorappapi.dto.request.RequestNoteImageRemoveDTO;
import lk.acpt.notecollectorappapi.dto.request.RequestUpdateNoteTitleAndDescriptionDTO;
import lk.acpt.notecollectorappapi.dto.response.ResponseNoteDTO;
import lk.acpt.notecollectorappapi.entity.Note;
import lk.acpt.notecollectorappapi.exception.ImageRemoveException;
import lk.acpt.notecollectorappapi.exception.ImageUploadException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface NoteService {
    Note saveNote(String title, String description, String dateTime, MultipartFile[] images) throws IOException;

    List<ResponseNoteDTO> getAllNotes();

    List<ResponseNoteDTO> searchNotes(String searchKeyword);

    Note updateNoteTitleAndDescription(RequestUpdateNoteTitleAndDescriptionDTO noteTitleAndDescriptionDTO);

    Note updateNoteByAddingImage(Integer noteId, String dateTime, MultipartFile[] images) throws ImageUploadException;

    Note updateNoteByRemovingImages(RequestNoteImageRemoveDTO noteImageRemoveDTO) throws ImageRemoveException;
}
