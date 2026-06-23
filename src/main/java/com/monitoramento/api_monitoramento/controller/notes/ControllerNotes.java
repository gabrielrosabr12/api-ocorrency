package com.monitoramento.api_monitoramento.controller.notes;

import com.monitoramento.api_monitoramento.entity.notes.Note;
import com.monitoramento.api_monitoramento.entity.notes.NoteDto;
import com.monitoramento.api_monitoramento.entity.users.User;
import com.monitoramento.api_monitoramento.interfaces.StorageService;
import com.monitoramento.api_monitoramento.services.NoteServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/notes")
@AllArgsConstructor
public class ControllerNotes {

    private final NoteServices noteServices;

    private final StorageService storageService;

    @PostMapping(value="/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createNotes(@AuthenticationPrincipal User user,
                                         @RequestPart("noteDto") NoteDto noteDto,
                                         @RequestParam("files") HashSet<MultipartFile> files){
        Set<Path> path = storageService.store(files);
        Note note = noteServices.createANote(user,noteDto,path);
        return ResponseEntity.ok(note);
    }

    @PostMapping("/post_attachment")
    public ResponseEntity<?> createAttachment(@RequestParam("file") Set<MultipartFile> arquivo){
        storageService.store(arquivo);
        System.out.println("Test carried out successfully");
        return ResponseEntity.ok("Test carried out successfully");
    }


}
