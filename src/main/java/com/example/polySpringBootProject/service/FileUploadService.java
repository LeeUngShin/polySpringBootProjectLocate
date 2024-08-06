package com.example.polySpringBootProject.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Getter
public class FileUploadService {

    private String originalFileName;
    private String fileExtension;
    private String saveDir;
    private String storedFileName;
    private String storedFileNameWithExtension;
    private String uploadPath;
    public boolean upload(MultipartFile file) {
        boolean result = false;

        originalFileName = file.getOriginalFilename();
        fileExtension =
                originalFileName.substring(originalFileName.lastIndexOf("."), originalFileName.length());
        saveDir = "C:/imgUploadF";
        File saveDirectory = new File(saveDir);
        if (!saveDirectory.exists()) {
            saveDirectory.mkdirs();
        }
        UUID uuid = UUID.randomUUID();
        storedFileName = uuid.toString().replaceAll("-", "");
        uploadPath = saveDir + "/" + storedFileName + fileExtension;
        storedFileNameWithExtension = storedFileName + fileExtension;
        File saveFile = new File(uploadPath);
        try {
            file.transferTo(saveFile);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return result;
    }

}
