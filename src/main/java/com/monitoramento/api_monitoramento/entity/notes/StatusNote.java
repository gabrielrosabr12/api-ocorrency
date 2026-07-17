package com.monitoramento.api_monitoramento.entity.notes;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="status_note")
public class StatusNote {

    @Id
    private Long id;

    @Column(name="status",length = 50)
    private String status;

    StatusNote(Long id, String status){
        this.id = id;
        this.status = status;
    }

    public enum Enum{
        EM_ANDAMENTO(1L,"EM ANDAMENTO"),
        EM_ABERTO(2L, "EM ABERTO"),
        CONCLUIDO(3L,"CONCLUIDO"),
        S_CONCLUSAO(4L, "SEM CONCLUSAO");

        private final Long id;
        private final String status;


        Enum(Long id, String status){
            this.id = id;
            this.status = status;
        }

        public StatusNote get(){
            return new StatusNote(id, status);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        StatusNote that = (StatusNote) o;
        return Objects.equals(id, that.id) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(status);
        return result;
    }
}
