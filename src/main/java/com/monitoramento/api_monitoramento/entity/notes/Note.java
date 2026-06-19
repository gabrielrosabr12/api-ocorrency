package com.monitoramento.api_monitoramento.entity.notes;


import com.monitoramento.api_monitoramento.entity.users.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title",columnDefinition = "TEXT", nullable = false)
    private String title;

    @Column(name="body")
    private String body;

    @ManyToOne
    @JoinColumn(name="created_by")
    private User createdBy;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(name="attachment_note",
    joinColumns = @JoinColumn(name="note_id"),
    inverseJoinColumns = @JoinColumn(name = "attachment_id"))
    private Set<Attachments> attachments = new HashSet<>();

}
