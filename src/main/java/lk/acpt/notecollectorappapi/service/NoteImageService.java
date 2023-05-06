package lk.acpt.notecollectorappapi.service;

import lk.acpt.notecollectorappapi.entity.NoteImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface NoteImageService {
    NoteImage addImage(MultipartFile image) throws IOException;

    void removeImage(List<NoteImage> noteImageList) throws IOException;
}
