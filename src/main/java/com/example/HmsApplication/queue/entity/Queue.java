package com.example.HmsApplication.queue.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "queues")
public class Queue {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String queueId;

    private String visitId;

    private String doctorId;

    private int queueNumber;

    @Enumerated(EnumType.STRING)
    private QueueStatus status;

    // -------------------
    // GETTERS & SETTERS
    // -------------------

    public String getQueueId() {
        return queueId;
    }

    public void setQueueId(String queueId) {
        this.queueId = queueId;
    }

    public String getVisitId() {
        return visitId;   // ✅ FIXED
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public int getQueueNumber() {
        return queueNumber;
    }

    public void setQueueNumber(int queueNumber) {
        this.queueNumber = queueNumber;
    }

    public QueueStatus getStatus() {
        return status;
    }

    public void setStatus(QueueStatus status) {
        this.status = status;
    }

    // ENUM
    public enum QueueStatus {
        WAITING,
        IN_PROGRESS,
        DONE
    }
}
