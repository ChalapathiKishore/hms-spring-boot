package com.example.HmsApplication.reception.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String paymentId;

    @Column(nullable = false)
    private String visitId;

    private double amount;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private LocalDateTime paymentTime;

    @PrePersist
    protected void onCreate() {
        paymentTime = LocalDateTime.now();
        paymentStatus = PaymentStatus.SUCCESS;
    }

    // --------------------
    // GETTERS & SETTERS
    // --------------------

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getVisitId() {
        return visitId;   // ✅ FIXED
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    // ENUMS
    public enum PaymentMode {
        CASH,
        CARD,
        UPI
    }

    public enum PaymentStatus {
        SUCCESS,
        FAILED
    }
}