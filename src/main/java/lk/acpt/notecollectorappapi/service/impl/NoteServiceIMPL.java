package lk.acpt.notecollectorappapi.service.impl;

import lk.acpt.notecollectorappapi.dto.request.RequestUpdateNoteTitleAndDescriptionDTO;
import lk.acpt.notecollectorappapi.dto.response.ResponseNoteDTO;
import lk.acpt.notecollectorappapi.entity.Note;
import lk.acpt.notecollectorappapi.entity.NoteImage;
import lk.acpt.notecollectorappapi.exception.ImageUploadException;
import lk.acpt.notecollectorappapi.exception.NotFoundException;
import lk.acpt.notecollectorappapi.repo.NoteRepo;
import lk.acpt.notecollectorappapi.service.NoteService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
public class NoteServiceIMPL implements NoteService {

    @Autowired
    private NoteRepo noteRepo;

    @Autowired
    private ModelMapper modelMapper;

    private final String FOLDER_PATH = "C:\\Users\\Ravindu\\Desktop\\AFSD\\NoteApp\\note-collector-app-api\\src\\main\\resources\\static\\images\\";

    @Override
    @Transactional
    public Note saveNote(String title, String description, String dateTime, MultipartFile[] images) throws ImageUploadException {
        Note note = new Note();
        note.setTitle(title);
        note.setDescription(description);
        note.setDateTime(dateTime);
        if (images!=null){
            List<NoteImage> noteImageList = new ArrayList<>();
            for (MultipartFile image : images){
                String imageName = UUID.randomUUID().toString().substring(0,8) + Objects.requireNonNull(image.getOriginalFilename()).replaceAll("\\s","");
                String imagePath = FOLDER_PATH + imageName;
                try {
                    image.transferTo(new File(imagePath));
                }catch (Exception e) {
                    System.err.println(e);
                    throw new ImageUploadException(e.getMessage());
                }
                NoteImage noteImage = new NoteImage();
                noteImage.setImageName(imageName);
                noteImage.setImagePath("http://localhost:8091/images/" +imageName);

                noteImageList.add(noteImage);
            }
            note.setNoteImages(noteImageList);
        }
        noteRepo.save(note);
        return note;
    }

    @Override
    public List<ResponseNoteDTO> getAllNotes() {
        List<Note> noteList = noteRepo.findAllByOrderByDateTimeDesc();
        List<ResponseNoteDTO> noteDTOList = modelMapper
                .map(noteList, new TypeToken<List<ResponseNoteDTO>>(){
                }.getType());
        return noteDTOList;
    }

    @Override
    public List<ResponseNoteDTO> searchNotes(String searchKeyword) {
        List<Note> noteList = noteRepo.searchNotes(searchKeyword);
        List<ResponseNoteDTO> noteDTOList = modelMapper
                .map(noteList, new TypeToken<List<ResponseNoteDTO>>(){
                }.getType());
        return noteDTOList;
    }

    @Override
    public Note updateNoteTitleAndDescription(RequestUpdateNoteTitleAndDescriptionDTO noteTitleAndDescriptionDTO) {
        if (noteRepo.existsById(noteTitleAndDescriptionDTO.getNoteId())){
            Note note = noteRepo.getNoteByNoteId(noteTitleAndDescriptionDTO.getNoteId());

            note.setTitle(noteTitleAndDescriptionDTO.getTitle());
            note.setDescription(noteTitleAndDescriptionDTO.getDescription());
            note.setDateTime(noteTitleAndDescriptionDTO.getDateTime());

            noteRepo.save(note);
            return note;
        } else {
            throw new NotFoundException("Note not found");
        }
    }

}
