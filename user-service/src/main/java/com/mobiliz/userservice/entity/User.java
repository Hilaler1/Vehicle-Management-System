package com.mobiliz.userservice.entity;


import com.mobiliz.userservice.entity.enums.Role;
import com.mobiliz.userservice.entity.enums.Status;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    @Email
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Company company;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    @ElementCollection
    private Set<Long> groupIds;
    @ElementCollection
    private Set<Long> vehicleIds;


    @PrePersist
    public void prePersist() {
        setCreatedAt(LocalDate.now());
        setUpdatedAt(LocalDate.now());
    }

    @PreUpdate
    public void preUpdate() {
        setUpdatedAt(LocalDate.now());
    }
}

