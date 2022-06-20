package com.jerrycodes.emis.service.impl;

import com.jerrycodes.emis.entity.Attachment;
import com.jerrycodes.emis.repository.AttachmentRepository;
import com.jerrycodes.emis.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AttachmentServiceImpl implements AttachmentService {
    private AttachmentRepository attachmentRepository;

    @Autowired
    public AttachmentServiceImpl(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public Attachment saveAttachment(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("...")){
                throw new Exception("Filename contains invalid path sequence" + fileName);
            }

            Attachment attachment = new Attachment(
                    fileName,
                    file.getContentType(),
                    file.getBytes());

             return attachmentRepository.save(attachment);


        }catch (Exception e){
            throw new Exception("could not save file"+ fileName);
        }

    }

    @Override
    public Attachment getAttachment(String fileId) throws RuntimeException {
        return attachmentRepository
                .findById(fileId)
                .orElseThrow(
                        () -> new RuntimeException("File not found with id:" + fileId));
    }
}
