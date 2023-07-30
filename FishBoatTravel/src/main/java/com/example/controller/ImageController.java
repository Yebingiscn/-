package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class ImageController {
    @RequestMapping("/user-image")
    public String upLoadImg(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "图片上传失败";
        } else {
            String originalFilename = file.getOriginalFilename();
            String fileName = "D:\\yb\\fish-boat-travel\\vue-test\\public\\Image\\User\\";
            File dest = new File(fileName + originalFilename);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            if (!dest.exists()) {
                file.transferTo(dest);
            }
            return "/Image/User/" + originalFilename;

        }
    }

    @RequestMapping("/road-image")
    public String upLoadRoadImg(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "图片上传失败";
        } else {
            String originalFilename = file.getOriginalFilename();
            String fileName = "D:\\yb\\fish-boat-travel\\vue-test\\public\\Image\\Road\\";
            File dest = new File(fileName + originalFilename);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
                System.out.println("test");
            }
            if (!dest.exists()) {
                file.transferTo(dest);
            }
            return "/Image/Road/" + originalFilename;
        }
    }
}
