package lk.acpt.notecollectorappapi.controller;

import lk.acpt.notecollectorappapi.dto.request.RequestUpdateNoteTitleAndDescriptionDTO;
import lk.acpt.notecollectorappapi.dto.response.ResponseNoteDTO;
import lk.acpt.notecollectorappapi.entity.Note;
import lk.acpt.notecollectorappapi.exception.NoteSaveException;
import lk.acpt.notecollectorappapi.service.NoteService;
import lk.acpt.notecollectorappapi.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/note")
@CrossOrigin
public class NoteController {
    @Autowired
    private NoteService noteService;

    @PostMapping("save")
    public ResponseEntity<StandardResponse> saveNote(@RequestParam("title") String title,
                                                     @RequestParam("description") String description,
                                                     @RequestParam("dateTime") String dateTime,
                                                     @RequestParam(value = "images", required = false) MultipartFile[] images) throws NoteSaveException {
        try {
            Note note = noteService.saveNote(title,description,dateTime,images);
            return new ResponseEntity<>(
                    new StandardResponse(201, "New Note Added", note.getNoteId()),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            throw new NoteSaveException(e.getMessage());
        }
    }

    @GetMapping("/get-all-notes")
    public ResponseEntity<StandardResponse> getAllNotes(){
        List<ResponseNoteDTO> noteDTOList = noteService.getAllNotes();
        return new ResponseEntity<>(
                new StandardResponse(200, "Success", noteDTOList),
                HttpStatus.OK
        );
    }

    @GetMapping("/search")
    public ResponseEntity<StandardResponse> searchNotes(@RequestParam(value = "searchKeyword") String searchKeyword){
        List<ResponseNoteDTO> noteDTOList = noteService.searchNotes(searchKeyword);
        return new ResponseEntity<>(
                new StandardResponse(200, "Success", noteDTOList),
                HttpStatus.OK
        );
    }

    @PutMapping("/update-title-description")
    public ResponseEntity<StandardResponse> updateNoteTitleAndDescription(@RequestBody RequestUpdateNoteTitleAndDescriptionDTO noteTitleAndDescriptionDTO){
        Note note = noteService.updateNoteTitleAndDescription(noteTitleAndDescriptionDTO);
        return new ResponseEntity<>(
                new StandardResponse(201, "Note Updated", note.getNoteId()),
                HttpStatus.CREATED
        );
    }
}
