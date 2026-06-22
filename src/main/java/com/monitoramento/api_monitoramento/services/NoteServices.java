package com.monitoramento.api_monitoramento.services;

import com.monitoramento.api_monitoramento.entity.notes.Attachments;
import com.monitoramento.api_monitoramento.entity.notes.Note;
import com.monitoramento.api_monitoramento.entity.notes.NoteDto;
import com.monitoramento.api_monitoramento.entity.users.User;
import com.monitoramento.api_monitoramento.mappers.NoteMapper;
import com.monitoramento.api_monitoramento.repository.notes.AttachmentsRepository;
import com.monitoramento.api_monitoramento.repository.notes.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class NoteServices {

    private final NoteRepository noteRepository;

    private final AttachmentsRepository attachmentsRepository;

    private final NoteMapper noteMapper;

    @Transactional
    public Note createANote(User user, NoteDto noteDto){
        Note note = noteMapper.toMapNote(noteDto);
        note.setCreatedBy(user);
        //(1) Step: Create a attachments if exists

        Set<Attachments> attachment = noteMapper.toMapAttachments(noteDto.attachments().get());
        note.setAttachments(attachment);

        return noteRepository.save(note);

    }

}
