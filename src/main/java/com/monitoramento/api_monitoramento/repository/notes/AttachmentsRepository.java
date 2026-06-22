package com.monitoramento.api_monitoramento.repository.notes;

import com.monitoramento.api_monitoramento.entity.notes.Attachments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentsRepository extends JpaRepository<Attachments,Long> {
}
