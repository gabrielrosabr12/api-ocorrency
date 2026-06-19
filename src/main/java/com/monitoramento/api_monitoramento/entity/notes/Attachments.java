package com.monitoramento.api_monitoramento.entity.notes;

import com.monitoramento.api_monitoramento.entity.users.User;
import jakarta.persistence.*;

@Entity
@Table(name="attachments")
public class Attachments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="file_path",length = 300)
    private String file_path;

    @ManyToOne
    @JoinColumn(name="created_at",referencedColumnName="id")
    private User created_at;

}
