package com.taxi.user.controller;

import com.taxi.user.dto.DriverRequest;
import com.taxi.user.dto.StatusUpdateRequest;
import com.taxi.user.entity.Driver;
import com.taxi.user.entity.Driver.DriverStatus;
import com.taxi.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Driver> register(@Valid @RequestBody DriverRequest req) {
        Driver driver = userService.registerDriver(req);
        return ResponseEntity.ok(driver);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> getById(@PathVariable Long id) {
        return ResponseEntity.of(userService.getDriver(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id,
                                             @Valid @RequestBody StatusUpdateRequest statusReq) {
        DriverStatus newStatus = DriverStatus.valueOf(statusReq.getStatus().toUpperCase());
        boolean updated = userService.updateDriverStatus(id, newStatus);
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/available")
    public List<Driver> getAvailable() {
        return userService.getAvailableDrivers();
    }
}