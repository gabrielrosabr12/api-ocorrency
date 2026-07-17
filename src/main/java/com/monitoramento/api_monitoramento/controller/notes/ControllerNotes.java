package com.monitoramento.api_monitoramento.controller.notes;

import com.monitoramento.api_monitoramento.entity.notes.Note;
import com.monitoramento.api_monitoramento.entity.notes.NoteDto;
import com.monitoramento.api_monitoramento.entity.users.User;
import com.monitoramento.api_monitoramento.services.NoteServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/notes")
public class ControllerNotes {

    @Autowired
    private NoteServices noteServices;

    @PostMapping("/create")
    public ResponseEntity<?> createNotes(@AuthenticationPrincipal User user,
                                         @RequestBody @Valid NoteDto noteDto){
        Note note = noteServices.createANote(user,noteDto);
        return ResponseEntity.ok(note);
    }


}
