package lk.acpt.notecollectorappapi.service.impl;

import lk.acpt.notecollectorappapi.dto.request.RequestNoteImageRemoveDTO;
import lk.acpt.notecollectorappapi.entity.Note;
import lk.acpt.notecollectorappapi.entity.NoteImage;
import lk.acpt.notecollectorappapi.exception.NotFoundException;
import lk.acpt.notecollectorappapi.exception.NoteUpdateException;
import lk.acpt.notecollectorappapi.repo.NoteImageRepo;
import lk.acpt.notecollectorappapi.repo.NoteRepo;
import lk.acpt.notecollectorappapi.service.NoteImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class NoteImageServiceIMPL implements NoteImageService {

    @Autowired
    private NoteImageRepo noteImageRepo;

    @Autowired
    private NoteRepo noteRepo;

    private final String FOLDER_PATH = "C:\\Users\\Ravindu\\Desktop\\AFSD\\NoteApp\\note-collector-app-api\\src\\main\\resources\\static\\images\\";


    @Override
    public Note updateNoteByRemovingImages(RequestNoteImageRemoveDTO noteImageRemoveDTO) throws NoteUpdateException {
        if (noteRepo.existsById(noteImageRemoveDTO.getNoteId())){
            Note note = noteRepo.getNoteByNoteId(noteImageRemoveDTO.getNoteId());
            note.setDateTime(noteImageRemoveDTO.getDateTime());
            List<NoteImage> noteImageList = noteImageRemoveDTO.getNoteImageList();
            for (NoteImage noteImage : noteImageList){
                noteImageRepo.deleteById(noteImage.getNoteImageId());
                String filePath = FOLDER_PATH+noteImage.getImageName();
                Path path = FileSystems.getDefault().getPath(filePath);
                try {
                    Files.deleteIfExists(path);
                } catch (Exception e){
                    System.err.println(e);
                    throw new NoteUpdateException(e.getMessage());
                }
            }
            return note;
        } else {
            throw new NotFoundException("No data found");
        }
    }
}
