package com.monitoramento.api_monitoramento.controller.notes;

import com.monitoramento.api_monitoramento.entity.notes.Note;
import com.monitoramento.api_monitoramento.entity.notes.NoteDto;
import com.monitoramento.api_monitoramento.entity.users.User;
import com.monitoramento.api_monitoramento.interfaces.StorageService;
import com.monitoramento.api_monitoramento.services.NoteServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController()
@RequestMapping("/notes")
@AllArgsConstructor
public class ControllerNotes {

    private final NoteServices noteServices;

    private final StorageService storageService;

    @PostMapping("/create")
    public ResponseEntity<?> createNotes(@AuthenticationPrincipal User user,
                                         @RequestBody @Valid NoteDto noteDto){
        Note note = noteServices.createANote(user,noteDto);
        return ResponseEntity.ok(note);
    }

    @PostMapping("/post_attachment")
    public ResponseEntity<?> createAttachment(@RequestParam("file") MultipartFile arquivo){
        storageService.store(arquivo);
        System.out.println("Test carried out successfully");
        return ResponseEntity.ok("Test carried out successfully");
    }


}
