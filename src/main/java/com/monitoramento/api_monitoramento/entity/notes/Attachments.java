package com.monitoramento.api_monitoramento.entity.notes;

import com.monitoramento.api_monitoramento.entity.users.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="attachments")
public class Attachments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="file_path",length = 300)
    private String file_path;


    //@JoinColumn(name="created_at",referencedColumnName="id")
    @Column(name="created_at")
    private LocalDateTime created_at;

    @PrePersist
    private void onCreate(){
        if (this.getCreated_at() == null){
            this.setCreated_at(LocalDateTime.now());
        }
    }


    public Attachments(String file_path){
        this.setFile_path(file_path);
    }

}
