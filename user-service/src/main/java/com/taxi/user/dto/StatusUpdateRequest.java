package com.taxi.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StatusUpdateRequest {
    @NotNull
    private String status;  // FREE, BUSY, OFFLINE
}