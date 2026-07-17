package com.monitoramento.api_monitoramento.entity.notes;


import com.monitoramento.api_monitoramento.entity.users.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="note")
@NoArgsConstructor
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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="attachment_note",
    joinColumns = @JoinColumn(name="note_id"),
    inverseJoinColumns = @JoinColumn(name = "attachment_id"))
    private Set<Attachments> attachments = new HashSet<>();

    public Note(String title, String body, LocalDateTime createdAt,Optional<Set<Attachments>> attachments){
        this.setBody(body);
        this.setTitle(title);
        this.setCreatedAt(createdAt);

        if (attachments != null && !attachments.isEmpty()) {
            this.attachments.addAll(attachments.get());
        }
    }

}
