package com.monitoramento.api_monitoramento.entity.notes;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="history_notes")
@NoArgsConstructor
public class HistoryNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name="note_id")
    private Note note;

    @Column(name="comment", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "datetime")
    private LocalDateTime dateTime;

}
