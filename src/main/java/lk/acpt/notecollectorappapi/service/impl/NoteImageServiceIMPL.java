package lk.acpt.notecollectorappapi.service.impl;

import lk.acpt.notecollectorappapi.entity.NoteImage;
import lk.acpt.notecollectorappapi.repo.NoteImageRepo;
import lk.acpt.notecollectorappapi.service.NoteImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class NoteImageServiceIMPL implements NoteImageService {

    @Autowired
    private NoteImageRepo noteImageRepo;

//    AWS path
//    private final String FOLDER_PATH = "/opt/apache-tomcat-8.5.88/webapps/note-collector-app-api-0.0.1-SNAPSHOT/WEB-INF/classes/noteImages/";

    //local path
    private final String FOLDER_PATH = "C:\\Users\\Ravindu\\Desktop\\AFSD\\NoteApp\\note-collector-app-api\\src\\main\\resources\\noteImages\\";

    @Override
    public NoteImage addImage(MultipartFile image) throws IOException {
        String imageName = UUID.randomUUID().toString().substring(0,8) + Objects.requireNonNull(image.getOriginalFilename()).replaceAll("\\s","");
        String imageFolderPath = FOLDER_PATH + imageName;
        image.transferTo(new File(imageFolderPath));
        NoteImage noteImage = new NoteImage();
        noteImage.setImageName(imageName);
        return noteImage;
    }

    @Override
    public void removeImage(List<NoteImage> noteImageList) throws IOException {
        for (NoteImage noteImage : noteImageList) {
            noteImageRepo.deleteById(noteImage.getNoteImageId());
            String filePath = FOLDER_PATH + noteImage.getImageName();
            Path path = FileSystems.getDefault().getPath(filePath);
            Files.deleteIfExists(path);
        }
    }

    @Override
    public byte[] downloadNoteImage(String fileName) throws IOException {
        return Files.readAllBytes(new File(FOLDER_PATH+fileName).toPath());
    }
}
