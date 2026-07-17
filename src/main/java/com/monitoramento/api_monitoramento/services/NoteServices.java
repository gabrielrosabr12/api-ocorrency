package com.monitoramento.api_monitoramento.services;

import com.monitoramento.api_monitoramento.entity.notes.Attachments;
import com.monitoramento.api_monitoramento.entity.notes.Note;
import com.monitoramento.api_monitoramento.entity.notes.dtos.NoteDto;
import com.monitoramento.api_monitoramento.entity.notes.StatusNote;
import com.monitoramento.api_monitoramento.entity.users.User;
import com.monitoramento.api_monitoramento.mappers.NoteMapper;
import com.monitoramento.api_monitoramento.repository.notes.AttachmentsRepository;
import com.monitoramento.api_monitoramento.repository.notes.NoteRepository;
import com.monitoramento.api_monitoramento.repository.notes.StatusNoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteServices {

    private final NoteRepository noteRepository;

    private final AttachmentsRepository attachmentsRepository;

    private final NoteMapper noteMapper;

    private final StatusNoteRepository statusNoteRepository;

    private final UsersServices usersServices;

    @Transactional
    public Note createANote(String username, NoteDto noteDto, Set<Path> pathAtachment){

        Note note = noteMapper.toMapNote(noteDto);
        User user = usersServices.loadUserByUsername(username);
        note.setStatusNote(StatusNote.Enum.EM_ABERTO.get());
        note.setCreatedBy(user);
        //(1) Step: Create a attachments if exists

        Set<Attachments> attachments = pathAtachment.stream().map(path -> {
            return new Attachments(path.toString());
        }).collect(Collectors.toSet());

        note.setAttachments(attachments);

        return noteRepository.save(note);

    }

    @Transactional
    public

}
