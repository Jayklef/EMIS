package com.jerrycodes.emis.controller;

import com.jerrycodes.emis.entity.Attachment;
import com.jerrycodes.emis.model.ResponseData;
import com.jerrycodes.emis.repository.AttachmentRepository;
import com.jerrycodes.emis.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("api/v1/attachments")
public class AttachmentController {

    private AttachmentService attachmentService;

    @Autowired
    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping("/upload")
    public ResponseData uploadFile(@RequestParam("file")MultipartFile file) throws Exception {
        Attachment attachment = null;
        String downloadURL = "";
        attachment = attachmentService.saveAttachment(file);
        downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(attachment.getFileId())
                .toUriString();

        return new ResponseData(
                attachment.getFileName(),
                downloadURL,
                file.getContentType(),
                file.getSize()
        );
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileId") String fileId){
        Attachment attachment = null;
        attachment = attachmentService.getAttachment(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" +
                                attachment.getFileName()+ "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }
}
