package com.example.HmsApplication.patient.repository;

import com.example.HmsApplication.patient.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, String> {

    // Search by name (partial match)
    List<Patient> findByFullNameContainingIgnoreCase(String fullName);

    // Search by mobile (exact)
    Optional<Patient> findByMobile(String mobile);

    // Duplicate checks
    boolean existsByAadharNumber(String aadharNumber);
    boolean existsByMobile(String mobile);
    boolean existsByRfidTagId(String rfidTagId);
}
