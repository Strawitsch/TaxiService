package com.taxi.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "drivers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String phone;
    private String licenseNumber;
    @Enumerated(EnumType.STRING)
    private DriverStatus status = DriverStatus.FREE;
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum DriverStatus {
        FREE, BUSY, OFFLINE
    }
}