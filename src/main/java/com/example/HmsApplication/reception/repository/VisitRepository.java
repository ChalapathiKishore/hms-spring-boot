package com.example.HmsApplication.reception.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.HmsApplication.reception.entity.Visit;

import java.util.Optional;

public interface VisitRepository extends JpaRepository<Visit, String> {

    Optional<Visit> findByPatientIdAndVisitStatus(
            String patientId,
            Visit.VisitStatus status
    );
}