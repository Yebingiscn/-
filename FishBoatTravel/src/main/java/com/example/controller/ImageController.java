package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.config.ResponseCodeConfig.IMAGE_UPLOAD_FAILURE;

@RestController
@RequestMapping("/upload")
public class ImageController {
    Logger logger;

    @RequestMapping("/user-image")
    public String upLoadImg(MultipartFile file) {
        if (file.isEmpty()) {
            return IMAGE_UPLOAD_FAILURE;
        } else {
            String originalFilename = file.getOriginalFilename();
            String fileName = "D:\\yb\\fish-boat-travel\\vue-test\\public\\Image\\User\\";
            return uploadImg(fileName, originalFilename, file);
        }
    }

    @RequestMapping("/road-image")
    public String upLoadRoadImg(MultipartFile file) {
        if (file.isEmpty()) {
            return IMAGE_UPLOAD_FAILURE;
        } else {
            String originalFilename = file.getOriginalFilename();
            String fileName = "D:\\yb\\fish-boat-travel\\vue-test\\public\\Image\\Road\\";
            return uploadImg(fileName, originalFilename, file);
        }
    }

    public String uploadImg(String fileName, String originalFilename, MultipartFile file) {
        File dest = new File(fileName + originalFilename);
        try {
            if (!dest.getParentFile().exists() && !dest.getParentFile().mkdirs()) {
                logger.log(Level.WARNING, "Failed to create directory");
                return IMAGE_UPLOAD_FAILURE;
            }
            file.transferTo(dest);
            return "/Image/User/" + originalFilename;
        } catch (Exception exception) {
            logger.log(Level.WARNING, exception.toString());
            return IMAGE_UPLOAD_FAILURE;
        }
    }
}
