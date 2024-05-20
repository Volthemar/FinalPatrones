package com.api.crud.DTO.Request;

public class LeaveCupoRequest {
    private Long cupoId;
    private boolean isOffline;

    // Getters and Setters
    public Long getCupoId() {
        return cupoId;
    }

    public void setCupoId(Long cupoId) {
        this.cupoId = cupoId;
    }

    public boolean isOffline() {
        return isOffline;
    }

    public void setOffline(boolean isOffline) {
        this.isOffline = isOffline;
    }
}
