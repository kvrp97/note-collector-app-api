package lk.acpt.notecollectorappapi.service.impl;

import lk.acpt.notecollectorappapi.dto.request.RequestNoteImageRemoveDTO;
import lk.acpt.notecollectorappapi.dto.request.RequestUpdateNoteTitleAndDescriptionDTO;
import lk.acpt.notecollectorappapi.dto.response.ResponseNoteDTO;
import lk.acpt.notecollectorappapi.entity.Note;
import lk.acpt.notecollectorappapi.entity.NoteImage;
import lk.acpt.notecollectorappapi.exception.ImageRemoveException;
import lk.acpt.notecollectorappapi.exception.ImageUploadException;
import lk.acpt.notecollectorappapi.exception.NotFoundException;
import lk.acpt.notecollectorappapi.repo.NoteRepo;
import lk.acpt.notecollectorappapi.service.NoteImageService;
import lk.acpt.notecollectorappapi.service.NoteService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class NoteServiceIMPL implements NoteService {

    @Autowired
    private NoteRepo noteRepo;

    @Autowired
    private NoteImageService noteImageService;

    @Autowired
    private ModelMapper modelMapper;

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
                try {
                    NoteImage noteImage = noteImageService.addImage(image);
                    noteImageList.add(noteImage);
                } catch (Exception e){
                    System.err.println(e);
                    throw new ImageUploadException(e.getMessage());
                }
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
            throw new NotFoundException("Note not found for id : "+noteTitleAndDescriptionDTO.getNoteId());
        }
    }

    @Override
    public Note updateNoteByAddingImage(Integer noteId, String dateTime, MultipartFile[] images) throws ImageUploadException {
        Note note = noteRepo.findById(noteId).orElseThrow(()-> new NotFoundException("Invalid note id : "+ noteId));
        note.setDateTime(dateTime);
        for (MultipartFile image : images){
            try {
                NoteImage noteImage = noteImageService.addImage(image);
                note.getNoteImages().add(noteImage);
            } catch (Exception e){
                System.err.println(e);
                throw new ImageUploadException(e.getMessage());
            }
        }
        noteRepo.save(note);
        return note;
    }

    @Override
    public Note updateNoteByRemovingImages(RequestNoteImageRemoveDTO noteImageRemoveDTO) throws ImageRemoveException {
        if (noteRepo.existsById(noteImageRemoveDTO.getNoteId())){
            Note note = noteRepo.getNoteByNoteId(noteImageRemoveDTO.getNoteId());
            note.setDateTime(noteImageRemoveDTO.getDateTime());
            List<NoteImage> noteImageList = noteImageRemoveDTO.getNoteImageList();
            try {
                noteImageService.removeImage(noteImageList);
            } catch (IOException e) {
                throw new ImageRemoveException(e.getMessage());
            }
            return note;
        } else {
            throw new NotFoundException("No Note found for id : "+noteImageRemoveDTO.getNoteId());
        }
    }

    @Override
    public boolean deleteNote(Integer noteId) throws ImageRemoveException {
        Note note = noteRepo.findById(noteId).orElseThrow(()-> new NotFoundException("Invalid note id : "+ noteId));
        List<NoteImage> noteImageList = note.getNoteImages();
        try {
            noteImageService.removeImage(noteImageList);
        } catch (IOException e) {
            throw new ImageRemoveException(e.getMessage());
        }
        noteRepo.deleteById(noteId);
        return true;
    }

}
