package com.example.warehouseapp.repository;

import com.example.warehouseapp.entity.attachment.Attachment;
import com.example.warehouseapp.entity.attachment.AttachmentContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Integer> {

    AttachmentContent findByAttachment(Attachment attachment);

    Optional<AttachmentContent> findByAttachment_Id(Integer id);
}
