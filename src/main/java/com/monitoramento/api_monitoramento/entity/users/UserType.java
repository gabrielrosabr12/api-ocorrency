package com.monitoramento.api_monitoramento.entity.users;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name="_user_type")
public class UserType {

    @Id
    private Long id;

    @Column(name="type",unique = true)
    private String type;

    public UserType(){
    }

    public UserType(Long id, String type){
        this.id = id;
        this.type = type;
    }

    public enum Enum{
        USER(1L,"user"),
        COLLABORATOR(2L,"collaborator"),
        ADMIN(3L,"administrator");

        private final Long id;
        private final String type;

        Enum(Long id,String type) {
            this.id = id;
            this.type = type;
        }

        public UserType get(){
            return new UserType(id, type);
        }
    }



    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        UserType userType = (UserType) o;
        return Objects.equals(id, userType.id) && Objects.equals(type, userType.type);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(type);
        return result;
    }
}
