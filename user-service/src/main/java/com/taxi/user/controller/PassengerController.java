package com.taxi.user.controller;

import com.taxi.user.dto.PassengerRequest;
import com.taxi.user.entity.Passenger;
import com.taxi.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passengers")
@RequiredArgsConstructor
public class PassengerController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Passenger> register(@Valid @RequestBody PassengerRequest req) {
        Passenger passenger = userService.registerPassenger(req);
        return ResponseEntity.ok(passenger);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getById(@PathVariable Long id) {
        return ResponseEntity.of(userService.getPassenger(id));
    }
}