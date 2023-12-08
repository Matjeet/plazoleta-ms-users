package com.pragma.powerup.infrastructure.out.jpa.entity;

import com.pragma.powerup.domain.Constants;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = Constants.USER)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {

    public static final String ROLE_ID = "role_id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String lastName;
    private String documentId;
    private String phoneNumber;
    private LocalDate birthDate;
    private String email;
    private String password;
    @ManyToOne
    @JoinColumn(name = ROLE_ID, nullable = false)
    private RoleEntity role;
}
