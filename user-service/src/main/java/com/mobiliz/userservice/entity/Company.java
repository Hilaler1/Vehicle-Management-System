package com.mobiliz.userservice.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "company")
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String companyName;
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    private List<User> userList;

}
