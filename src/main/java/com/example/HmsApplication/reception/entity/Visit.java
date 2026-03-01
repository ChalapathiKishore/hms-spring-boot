package com.example.HmsApplication.reception.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visits")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String visitId;

    @Column(nullable = false)
    private String patientId;

    private String visitType;

    private String assignedDoctorId;

    private LocalDateTime visitDate;

    @Enumerated(EnumType.STRING)
    private VisitStatus visitStatus;

    @PrePersist
    protected void onCreate() {
        visitDate = LocalDateTime.now();
        visitStatus = VisitStatus.ACTIVE;
    }

    // -------------------
    // GETTERS & SETTERS
    // -------------------

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getPatientId() {
        return patientId;   // ✅ CORRECT
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public String getAssignedDoctorId() {
        return assignedDoctorId;
    }

    public void setAssignedDoctorId(String assignedDoctorId) {
        this.assignedDoctorId = assignedDoctorId;
    }

    public LocalDateTime getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDateTime visitDate) {
        this.visitDate = visitDate;
    }

    public VisitStatus getVisitStatus() {
        return visitStatus;
    }

    public void setVisitStatus(VisitStatus visitStatus) {
        this.visitStatus = visitStatus;
    }

    // ENUM
    public enum VisitStatus {
        ACTIVE,
        COMPLETED,
        CANCELLED
    }
}
