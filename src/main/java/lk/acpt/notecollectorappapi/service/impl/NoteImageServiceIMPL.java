package lk.acpt.notecollectorappapi.service.impl;

import lk.acpt.notecollectorappapi.dto.request.RequestNoteImageRemoveDTO;
import lk.acpt.notecollectorappapi.entity.Note;
import lk.acpt.notecollectorappapi.entity.NoteImage;
import lk.acpt.notecollectorappapi.exception.NotFoundException;
import lk.acpt.notecollectorappapi.repo.NoteImageRepo;
import lk.acpt.notecollectorappapi.repo.NoteRepo;
import lk.acpt.notecollectorappapi.service.NoteImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteImageServiceIMPL implements NoteImageService {

    @Autowired
    private NoteImageRepo noteImageRepo;

    @Autowired
    private NoteRepo noteRepo;

    @Override
    public Note updateNoteByRemovingImages(RequestNoteImageRemoveDTO noteImageRemoveDTO) {
        if (noteRepo.existsById(noteImageRemoveDTO.getNoteId())){
            Note note = noteRepo.getNoteByNoteId(noteImageRemoveDTO.getNoteId());
//            note.setDateTime(noteImageRemoveDTO.getDateTime());
            List<NoteImage> noteImageList = noteImageRemoveDTO.getNoteImageList();
            for (NoteImage noteImage : noteImageList){
                System.out.println(noteImage.getNoteImageId()+"  "+noteImage.getImageName()+"  "+noteImage.getImagePath());
            }
            return note;
        } else {
            throw new NotFoundException("No data found");
        }
    }
}
