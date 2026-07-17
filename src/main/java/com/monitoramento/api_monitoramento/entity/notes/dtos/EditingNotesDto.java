package com.monitoramento.api_monitoramento.entity.notes.dtos;

import com.monitoramento.api_monitoramento.entity.notes.StatusNote;

import java.util.Optional;

public record EditingNotesDto(Optional<String> body, String comment, StatusNote.Enum statusNote) {
}
