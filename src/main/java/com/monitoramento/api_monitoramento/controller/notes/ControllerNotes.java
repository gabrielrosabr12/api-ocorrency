package com.monitoramento.api_monitoramento.controller.notes;

import com.monitoramento.api_monitoramento.entity.notes.Note;
import com.monitoramento.api_monitoramento.entity.notes.dtos.EditingNotesDto;
import com.monitoramento.api_monitoramento.entity.notes.dtos.NoteDto;
import com.monitoramento.api_monitoramento.interfaces.StorageService;
import com.monitoramento.api_monitoramento.services.NoteServices;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

@RestController()
@RequestMapping("/notes")
@AllArgsConstructor
public class ControllerNotes {

    private final NoteServices noteServices;

    private final StorageService storageService;

    @PostMapping(value="/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createNotes(@AuthenticationPrincipal Jwt jwt,
                                         @RequestPart("noteDto") NoteDto noteDto,
                                         @RequestParam("files") HashSet<MultipartFile> files){
        Set<Path> path = storageService.store(files,noteDto.title());
        Note note = noteServices.createANote(jwt.getSubject(),noteDto,path);
        return ResponseEntity.ok(note);
    }

    @PostMapping(value="/comment")
    public ResponseEntity<?> commentInNotes(@AuthenticationPrincipal Jwt jwt,
                                            @RequestBody EditingNotesDto editingNotesDto){


        return ResponseEntity.ok();
    }

    @PostMapping("/post_attachment")
    public ResponseEntity<?> createAttachment(@RequestParam("file") Set<MultipartFile> arquivo){
        storageService.store(arquivo,"testes");
        System.out.println("Test carried out successfully");
        return ResponseEntity.ok("Test carried out successfully");
    }


}
