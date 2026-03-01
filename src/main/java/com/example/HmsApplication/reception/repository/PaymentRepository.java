package com.example.HmsApplication.reception.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.HmsApplication.reception.entity.Payment;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, String> {

    Optional<Payment> findByVisitId(String visitId);
}