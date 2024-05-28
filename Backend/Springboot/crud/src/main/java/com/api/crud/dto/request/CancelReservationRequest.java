package com.api.crud.dto.request;

public class CancelReservationRequest {
    private Long cupoId;

    // Getters and Setters
    public Long getCupoId() {
        return cupoId;
    }

    public void setCupoId(Long cupoId) {
        this.cupoId = cupoId;
    }
}
