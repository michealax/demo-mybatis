package com.shane.mybatis.controller;

import com.shane.mybatis.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload/simple")
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

    @GetMapping("/download/simple")
    public void downloadSimple(HttpServletResponse response) {
        response.reset();
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader("Content-disposition", "attachment;filename=file_" + System.currentTimeMillis() + ".json");
        File file = new File("E:\\Documents\\server\\mybatis-demo\\tsconfig.json");
        try (FileInputStream inputStream = new FileInputStream(file)) {
            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) > 0) {
                response.getOutputStream().write(buf, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
