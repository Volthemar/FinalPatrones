package com.api.crud.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "Cupo_Offline")
public class CupoOffline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String status;
    
    @Column
    private String walkInUser;
    
    @Column
    private Date occupiedAt;

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

    public Date getOccupiedAt() {
        return occupiedAt;
    }

    public void setOccupiedAt(Date occupiedAt) {
        this.occupiedAt = occupiedAt;
    }
}
