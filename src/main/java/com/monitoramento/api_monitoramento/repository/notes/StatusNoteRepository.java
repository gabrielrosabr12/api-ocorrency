package com.monitoramento.api_monitoramento.repository.notes;

import com.monitoramento.api_monitoramento.entity.notes.StatusNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusNoteRepository extends JpaRepository<StatusNote,Long> {
}
