package com.mehmetpekdemir.jwt.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

/**
 * @author MEHMET PEKDEMIR
 * @since 1.0
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "role")
    private List<UserEntity> users;

}
