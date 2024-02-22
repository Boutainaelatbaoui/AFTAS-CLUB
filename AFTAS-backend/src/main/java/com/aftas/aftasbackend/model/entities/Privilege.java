package com.aftas.aftasbackend.model.entities;

import com.aftas.aftasbackend.enums.Permission;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "privileges")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Permission name;

    @JsonBackReference
    @ManyToMany(mappedBy = "privileges", fetch = FetchType.EAGER)
    private List<Role> roles;
}

