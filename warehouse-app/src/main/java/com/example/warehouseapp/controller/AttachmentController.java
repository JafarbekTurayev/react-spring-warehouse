package com.example.warehouseapp.controller;

import com.example.warehouseapp.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/upload")
    public HttpEntity<?> upload(MultipartHttpServletRequest request)  {

        return ResponseEntity.ok(attachmentService.uploadTo(request));

    }

    @GetMapping("/download/{id}")
    public HttpEntity<?> download(@PathVariable Integer id){
        return attachmentService.download(id);
    }


}
