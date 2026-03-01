package com.example.HmsApplication.reception.service;

import org.springframework.stereotype.Service;

import com.example.HmsApplication.reception.entity.Visit;
import com.example.HmsApplication.reception.repository.VisitRepository;
import com.example.HmsApplication.patient.repository.PatientRepository;

@Service
public class VisitService {

    private VisitRepository visitRepository;
    private PatientRepository patientRepository;

    // Manual constructor injection (NO Lombok)
    public VisitService(VisitRepository visitRepository,
                        PatientRepository patientRepository) {
        this.visitRepository = visitRepository;
        this.patientRepository = patientRepository;
    }

    public Visit createVisit(Visit visit) {

        // 1️⃣ Check patient exists
        if (!patientRepository.existsById(visit.getPatientId())) {
            throw new RuntimeException("Patient not found");
        }

        // 2️⃣ Check active visit exists
        if (visitRepository.findByPatientIdAndVisitStatus(
                visit.getPatientId(),
                Visit.VisitStatus.ACTIVE).isPresent()) {

            throw new RuntimeException("Patient already has active visit");
        }

        return visitRepository.save(visit);
    }
}