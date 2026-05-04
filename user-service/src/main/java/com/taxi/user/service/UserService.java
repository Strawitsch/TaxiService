package com.taxi.user.service;

import com.taxi.user.dto.*;
import com.taxi.user.entity.*;
import com.taxi.user.entity.Driver.DriverStatus;
import com.taxi.user.repository.*;
import com.taxi.user.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PassengerRepository passengerRepo;
    private final DriverRepository driverRepo;
    private final JwtTokenProvider jwtTokenProvider;

    public Passenger registerPassenger(PassengerRequest req) {
        Passenger p = Passenger.builder()
                .name(req.getName())
                .email(req.getEmail())
                .phone(req.getPhone())
                .build();
        return passengerRepo.save(p);
    }

    public Optional<Passenger> getPassenger(Long id) {
        return passengerRepo.findById(id);
    }

    public Driver registerDriver(DriverRequest req) {
        Driver d = Driver.builder()
                .name(req.getName())
                .email(req.getEmail())
                .phone(req.getPhone())
                .licenseNumber(req.getLicenseNumber())
                .status(DriverStatus.FREE)
                .build();
        return driverRepo.save(d);
    }

    public Optional<Driver> getDriver(Long id) {
        return driverRepo.findById(id);
    }

    @Transactional
    public boolean updateDriverStatus(Long id, DriverStatus newStatus) {
        int rows = driverRepo.updateStatusAtomic(id, DriverStatus.FREE, newStatus);
        return rows > 0;
    }

    public List<Driver> getAvailableDrivers() {
        return driverRepo.findByStatus(DriverStatus.FREE);
    }

    public String generateToken(Long userId, String role) {
        return jwtTokenProvider.generateToken(userId.toString(), role);
    }
}