package com.mobiliz.vehicleservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mobiliz.vehicleservice.entity.enums.Status;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String plateNumber; // Plaka
    private String chassisNumber; // Şase Numarası
    private String tag; // Etiket
    @Column(nullable = false)
    private String brand; // Marka
    @Column(nullable = false)
    private String model; // Model
    @Column(nullable = false)
    private int modelYear; // Model Yılı
    @ManyToOne
    @JsonManagedReference
    private VehicleGroup vehicleGroup;
    private Long companyId;
    @Enumerated(EnumType.STRING)
    private Status status;

}
