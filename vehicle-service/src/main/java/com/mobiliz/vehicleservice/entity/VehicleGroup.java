package com.mobiliz.vehicleservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_vehicle_group")
public class VehicleGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "vehicleGroup")
    @JsonBackReference
    private List<Vehicle> vehicleList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentGroup")
    private List<VehicleGroup> subGroups;

    @ManyToOne
    @JoinColumn(name = "parent_group_id")
    private VehicleGroup parentGroup;



}
