package com.taxi.user.repository;

import com.taxi.user.entity.Driver;
import com.taxi.user.entity.Driver.DriverStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    List<Driver> findByStatus(DriverStatus status);

    @Modifying
    @Query("UPDATE Driver d SET d.status = :newStatus WHERE d.id = :id AND d.status = :currentStatus")
    int updateStatusAtomic(@Param("id") Long id,
                           @Param("currentStatus") DriverStatus currentStatus,
                           @Param("newStatus") DriverStatus newStatus);
}