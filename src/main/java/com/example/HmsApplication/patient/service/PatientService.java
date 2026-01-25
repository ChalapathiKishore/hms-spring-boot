package com.example.HmsApplication.patient.service;

import com.example.HmsApplication.patient.entity.Patient;
import com.example.HmsApplication.patient.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(String patientId) {
        return patientRepository.findById(patientId);
    }
    public Optional<Patient> getPatientByMobile(String mobile) {
        return patientRepository.findByMobile(mobile);
    }
    public Optional<Patient> getPatientByAadhar(String aadharNumber) {
        return patientRepository.findByAadharNumber(aadharNumber);
    }



}
