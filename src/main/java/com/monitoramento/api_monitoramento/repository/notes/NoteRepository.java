package com.monitoramento.api_monitoramento.repository.notes;

import com.monitoramento.api_monitoramento.entity.notes.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note,Long> {
}
