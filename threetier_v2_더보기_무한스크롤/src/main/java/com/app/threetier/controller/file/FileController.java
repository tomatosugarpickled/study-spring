package com.app.threetier.controller.file;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/files/**")
public class FileController {
    @GetMapping("display")
    public byte[] display(String filePath, String fileName) throws IOException {
        return FileCopyUtils.copyToByteArray(new File("C:/file/" + filePath, fileName));
    }
}















