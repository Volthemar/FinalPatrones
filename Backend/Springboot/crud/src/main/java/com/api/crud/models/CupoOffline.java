package com.api.crud.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Cupo_Offline")
public class CupoOffline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;
    private String walkInUser;
    private Timestamp occupiedAt;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWalkInUser() {
        return walkInUser;
    }

    public void setWalkInUser(String walkInUser) {
        this.walkInUser = walkInUser;
    }

    public Timestamp getOccupiedAt() {
        return occupiedAt;
    }

    public void setOccupiedAt(Timestamp occupiedAt) {
        this.occupiedAt = occupiedAt;
    }
}
