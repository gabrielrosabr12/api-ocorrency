package com.monitoramento.api_monitoramento.repository.notes;

import com.monitoramento.api_monitoramento.entity.notes.HistoryNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryNoteRepository extends JpaRepository<HistoryNote,Long> {
}
