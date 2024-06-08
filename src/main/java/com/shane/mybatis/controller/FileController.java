package com.shane.mybatis.controller;

import com.shane.mybatis.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/simple")
    public ResponseResult<String> uploadSimpleFile(@RequestParam(value = "file") MultipartFile file) {
        try {
            String uploadPath = "E:\\Documents\\server\\mybatis-demo";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            log.info(uploadDir.getAbsolutePath());

            File localFile = new File(uploadPath + File.separator + file.getOriginalFilename());

            file.transferTo(localFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseResult.success("Upload file successfully");
    }
}
