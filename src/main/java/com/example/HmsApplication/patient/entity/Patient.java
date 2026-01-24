package com.example.HmsApplication.patient.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(
        name = "patient",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "aadhar_number"),
                @UniqueConstraint(columnNames = "mobile"),
                @UniqueConstraint(columnNames = "rfid_tag_id")
        }
)
public class Patient {

    /* ================= PRIMARY KEY ================= */

    @Id
    @NotBlank(message = "Patient ID is required")
    @Size(max = 10, message = "Patient ID must be at most 10 characters")
    @Column(name = "patient_id", length = 10, nullable = false)
    private String patientId;

    /* ================= OPTIONAL UNIQUE FIELDS ================= */

    @Size(max = 20)
    @Column(name = "rfid_tag_id", length = 20, unique = true)
    private String rfidTagId;

    @Size(max = 12)
    @Column(name = "aadhar_number", length = 12, unique = true)
    private String aadharNumber;

    /* ================= BASIC DETAILS ================= */

    @NotBlank(message = "Full name is required")
    @Size(max = 100, message = "Full name must be at most 100 characters")
    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    @NotNull(message = "Gender is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    private LocalDate dob;

    private Integer age;

    @Column(name = "blood_group", length = 3)
    private String bloodGroup;

    @Size(max = 15)
    @Column(length = 15, unique = true)
    private String mobile;

    @NotNull(message = "Patient type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_patient", nullable = false)
    private PatientType typeOfPatient;

    /* ================= AUDIT FIELD ================= */

    @Column(name = "registration_date", updatable = false)
    private LocalDateTime registrationDate;

    @PrePersist
    protected void onCreate() {
        this.registrationDate = LocalDateTime.now();
    }

    /* ================= GETTERS & SETTERS ================= */

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getRfidTagId() {
        return rfidTagId;
    }

    public void setRfidTagId(String rfidTagId) {
        this.rfidTagId = rfidTagId;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public PatientType getTypeOfPatient() {
        return typeOfPatient;
    }

    public void setTypeOfPatient(PatientType typeOfPatient) {
        this.typeOfPatient = typeOfPatient;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
}
