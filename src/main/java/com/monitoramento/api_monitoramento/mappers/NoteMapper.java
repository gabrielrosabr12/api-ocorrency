package com.monitoramento.api_monitoramento.mappers;

import com.monitoramento.api_monitoramento.entity.notes.Attachments;
import com.monitoramento.api_monitoramento.entity.notes.AttachmentsDto;
import com.monitoramento.api_monitoramento.entity.notes.Note;
import com.monitoramento.api_monitoramento.entity.notes.NoteDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class NoteMapper {

    public Note toMapNote(NoteDto noteDto){
        //Optional<Set<Attachments>> attachments = noteDto.attachments().map(this::toMapAttachments);
        return new Note(noteDto.title(),noteDto.body(), LocalDateTime.now(),null);
    }

    public Set<Attachments> toMapAttachments(AttachmentsDto attachmentsDto) {
        Set<Attachments> attachments = null;
        if (!attachmentsDto.file_paths().isEmpty()) {
            attachments = attachmentsDto.file_paths().stream().map(Attachments::new).collect(Collectors.toSet());
        }
        return attachments;
    }
}
