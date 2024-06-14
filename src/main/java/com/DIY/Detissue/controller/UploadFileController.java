package com.DIY.Detissue.controller;


import com.DIY.Detissue.exception.CustomFileNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/file")
public class UploadFileController {

//    Chứa toàn bộ hàm hỗ trợ sẵn liên quan tới đường dẫn

    @Value("${path.root}")
    private String spath;

    @GetMapping("/{fileName}")
    public ResponseEntity<?> loadFile(@PathVariable String fileName) {
//      Đường dẫn folder root lưu hình
        try {
            Path rootPath = Paths.get(spath);
            Resource resource = new UrlResource(rootPath.resolve(fileName).toUri());
            if (resource.exists()) {
//      Nếu tồn tại thì mới cho phép download
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
//      Khi ném exception thì code sẽ dừng và văng ra lỗi
                throw new CustomFileNotFoundException(200, "file not found");
            }
        } catch (Exception e) {
            throw new CustomFileNotFoundException(200, "file not found");
        }
    }

    @PostMapping("/uploadfile")
    public ResponseEntity<?> uploadFile(
            @RequestParam MultipartFile file
    ) {
//      Định nghĩa đường dẫn
        Path rootPath = Paths.get(spath);

        try {
            if (!Files.exists(rootPath)) {
//      Tạo folder ứng với lại đường dẫn nếu không tồn tại folder
                Files.createDirectories(rootPath);
            }

//      Resolve tương đương với dấu /
//      file.getOriginalFilename() : lấy tên file và định dạng
            String fileName = file.getOriginalFilename();
            Files.copy(file.getInputStream(), rootPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new CustomFileNotFoundException(500,"Khong tim thay file");
        }

        return new ResponseEntity<>("", HttpStatus.OK);
    }

}

