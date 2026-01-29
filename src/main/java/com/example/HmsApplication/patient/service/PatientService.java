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

    // UPDATE ✅
    public Patient updatePatient(String patientId, Patient updatedPatient) {

        Patient existingPatient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + patientId));

        // ❌ Patient ID must NOT change
        existingPatient.setFullName(updatedPatient.getFullName());
        existingPatient.setGender(updatedPatient.getGender());
        existingPatient.setAge(updatedPatient.getAge());
        existingPatient.setDob(updatedPatient.getDob());
        existingPatient.setBloodGroup(updatedPatient.getBloodGroup());
        existingPatient.setMobile(updatedPatient.getMobile());
        existingPatient.setAadharNumber(updatedPatient.getAadharNumber());
        existingPatient.setRfidTagId(updatedPatient.getRfidTagId());
        existingPatient.setTypeOfPatient(updatedPatient.getTypeOfPatient());

        return patientRepository.save(existingPatient);
    }

    // DELETE ✅
    public void deletePatient(String patientId) {
        if (!patientRepository.existsById(patientId)) {
            throw new RuntimeException("Patient not found with ID: " + patientId);
        }
        patientRepository.deleteById(patientId);
    }


}
