package jpa.basic.projectcloud.profile.controller;

import jpa.basic.projectcloud.profile.dto.FileDownloadUrlResponse;
import jpa.basic.projectcloud.profile.dto.FileUploadResponse;
import jpa.basic.projectcloud.profile.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class FileController {

    private final S3Service s3Service;

    @PostMapping("/{userId}/profile-image")
    public ResponseEntity<FileUploadResponse> upload(@RequestParam("file") MultipartFile file) {
        String key = s3Service.upload(file);
        return ResponseEntity.ok(new FileUploadResponse(key));
    }

    @GetMapping("/{userId}/profile-image")
    public ResponseEntity<FileDownloadUrlResponse> getDownloadUrl(@RequestParam String key) {
        URL url = s3Service.getDownloadUrl(key);
        return ResponseEntity.ok(new FileDownloadUrlResponse(url.toString()));
    }
}
