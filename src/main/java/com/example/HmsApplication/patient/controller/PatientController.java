package com.example.HmsApplication.patient.controller;

import com.example.HmsApplication.patient.entity.Patient;
import com.example.HmsApplication.patient.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // 1️⃣ Create Patient
    @PostMapping
    public ResponseEntity<Patient> createPatient(
            @Valid @RequestBody Patient patient) {

        Patient savedPatient = patientService.savePatient(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
    }

    // 2️⃣ Get All Patients
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    // 3️⃣ Get Patient by ID
    @GetMapping("/{patientId}")
    public ResponseEntity<Patient> getPatientById(
            @PathVariable String patientId) {

        Patient patient = patientService.getPatientById(patientId)
                .orElseThrow(() ->
                        new RuntimeException("Patient not found with ID: " + patientId)
                );

        return ResponseEntity.ok(patient);
    }
    @GetMapping("/search/by-mobile")
    public ResponseEntity<Patient> getByMobile(@RequestParam String mobile) {
        return patientService.getPatientByMobile(mobile)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/by-aadhar")
    public ResponseEntity<Patient> getByAadhar(@RequestParam String aadharNumber) {
        return patientService.getPatientByAadhar(aadharNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
