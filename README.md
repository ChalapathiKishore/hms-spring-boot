# 🏥 Hospital Workflow Management System

A backend **Hospital Workflow Management System** developed using **Java, Spring Boot, Spring Data JPA, Hibernate, and MySQL**. The system manages patients, visits, doctors, appointments/workflow, and hospital records while enforcing business rules such as checking whether a patient already has an active visit.

## 🚀 Project Overview

The Hospital Workflow Management System is designed to digitize and simplify common hospital operations.

The application provides REST APIs through which hospital staff can:

* Register new patients
* Search existing patients
* Create and manage patient visits
* Assign doctors
* Track patient workflow
* Maintain visit history
* Check for an existing active visit
* Store and retrieve hospital information from MySQL

The project follows a layered architecture using **Controller → Service → Repository → Database**.

---

## 🛠️ Technologies Used

| Technology      | Purpose                       |
| --------------- | ----------------------------- |
| Java            | Programming language          |
| Spring Boot     | Backend application framework |
| Spring Web      | REST API development          |
| Spring Data JPA | Database operations           |
| Hibernate       | ORM / entity management       |
| MySQL           | Relational database           |
| Maven           | Dependency management         |
| Postman         | API testing                   |
| Git & GitHub    | Version control               |

---

## 🏗️ Architecture

```text
                Client / Postman
                       |
                       ↓
              REST Controller
                       |
                       ↓
                 Service Layer
                       |
                       ↓
               Repository Layer
                       |
                       ↓
                 Hibernate/JPA
                       |
                       ↓
                    MySQL
```

### Layer Responsibilities

**Controller Layer**

* Receives HTTP requests
* Validates request data
* Returns HTTP responses

**Service Layer**

* Contains business logic
* Checks hospital workflow rules
* Coordinates between controllers and repositories

**Repository Layer**

* Communicates with the database
* Uses Spring Data JPA methods

**Entity Layer**

* Represents database tables using JPA entities

---

## 📌 Main Features

### 1. Patient Registration

A patient can be registered using details such as:

* Patient name
* Aadhaar number
* Mobile number
* Date of birth
* Gender
* Address
* Other required patient information

The Aadhaar number is maintained as a unique field to prevent duplicate patient registration.

---

### 2. Patient Search

Existing patients can be searched using their unique information.

Example:

```http
GET /patients/{id}
```

The system retrieves the patient details from the database.

---

### 3. Visit Management

A visit represents a patient's interaction with the hospital.

When a patient arrives, the system creates a visit record containing information such as:

```text
Patient
Visit ID
Visit Date
Doctor
Visit Status
```

The visit table maintains the patient's visit history.

---

### 4. Active Visit Check

Before creating a new visit, the system checks whether the patient already has an active visit.

Conceptually:

```text
Patient arrives
      ↓
Find patient
      ↓
Check Visit table
      ↓
Does patient have an ACTIVE visit?
      ↓
   ┌──Yes──→ Do not create duplicate active visit
   │
   No
   ↓
Create new visit
```

For example, the repository can use a query similar to:

```java
boolean existsByPatientIdAndStatus(
        Long patientId,
        VisitStatus status
);
```

The service layer uses this information to enforce the business rule.

This prevents the same patient from having multiple active visits at the same time.

---

## 🔄 Complete Workflow

```text
Patient Registration
        ↓
Patient Stored in Database
        ↓
Patient Visits Hospital
        ↓
Search Existing Patient
        ↓
Check Active Visit
        ↓
 ┌──────────────────────┐
 │ Active Visit Exists? │
 └──────────────────────┘
       ↓           ↓
      YES          NO
       ↓           ↓
Return Existing   Create Visit
Visit / Reject       ↓
                  Assign Doctor
                     ↓
                Process Patient
                     ↓
              Update Visit Status
                     ↓
                Visit Completed
```

---

## 🗄️ Database Design

The application uses **MySQL** as the relational database.

The main tables include:

```text
Patient
   |
   | 1
   |
   | *
 Visit
```

### Patient

Stores permanent patient information.

Example fields:

```text
id
name
aadhaar_number
mobile_number
date_of_birth
gender
address
```

### Visit

Stores individual hospital visits.

Example fields:

```text
id
patient_id
visit_date
doctor_id
status
```

A single patient can have multiple visits over time.

Example:

```text
Patient
  |
  ├── Visit 1 → COMPLETED
  ├── Visit 2 → COMPLETED
  └── Visit 3 → ACTIVE
```

The relationship is therefore:

```text
One Patient → Many Visits
```

---

## 🔐 Business Rules

The application implements important business validations.

### Duplicate Patient Prevention

A unique constraint is applied to the Aadhaar number.

```text
Aadhaar Number
      ↓
Already exists?
   ↓       ↓
 YES      NO
 ↓         ↓
Reject    Create
```

### Active Visit Validation

A patient should not have multiple active visits simultaneously.

```text
Patient
   ↓
Search ACTIVE visit
   ↓
Exists?
 ↓       ↓
Yes      No
 ↓        ↓
Reject   Create
         new visit
```

---

## 🌐 REST API Example

### Register Patient

```http
POST /patients
```

Request:

```json
{
  "name": "Kishore",
  "aadhaarNumber": "450060884994",
  "mobileNumber": "9876543210"
}
```

Response:

```json
{
  "id": 1,
  "name": "Kishore",
  "aadhaarNumber": "450060884994",
  "mobileNumber": "9876543210"
}
```

---

### Create Visit

```http
POST /visits
```

Example request:

```json
{
  "patientId": 1,
  "doctorId": 10
}
```

The service first checks:

```text
Is patient available?
        ↓
Does patient have ACTIVE visit?
        ↓
No → Create visit
```

---

## 📂 Project Structure

```text
src
 └── main
      └── java
           └── com.example.hospital
                │
                ├── controller
                │    ├── PatientController.java
                │    └── VisitController.java
                │
                ├── service
                │    ├── PatientService.java
                │    └── VisitService.java
                │
                ├── repository
                │    ├── PatientRepository.java
                │    └── VisitRepository.java
                │
                ├── entity
                │    ├── Patient.java
                │    └── Visit.java
                │
                ├── dto
                │    └── ...
                │
                └── HospitalApplication.java
```

---

## ⚙️ Spring Boot Concepts Used

This project demonstrates several important Spring Boot concepts:

* `@SpringBootApplication`
* `@RestController`
* `@Service`
* `@Repository`
* `@Entity`
* `@Id`
* `@GeneratedValue`
* `@OneToMany`
* `@ManyToOne`
* `@Transactional`
* Dependency Injection
* Constructor Injection
* Spring Data JPA
* REST APIs
* Exception Handling
* Validation
* Application Properties
* Auto Configuration

---

## 🔄 Dependency Injection

Instead of manually creating objects:

```java
PatientService service = new PatientService();
```

Spring manages the objects and injects the required dependencies.

Example:

```java
@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
}
```

This makes the application loosely coupled and easier to test and maintain.

---

## 🧠 Business Logic Example

The service layer is responsible for checking whether an active visit already exists.

```java
if (visitRepository.existsByPatientIdAndStatus(
        patientId, VisitStatus.ACTIVE)) {

    throw new RuntimeException(
        "Patient already has an active visit"
    );
}
```

Only after passing this validation is the new visit saved.

---

## 🗃️ JPA Relationship

The patient and visit relationship can be represented as:

```java
@OneToMany(mappedBy = "patient")
private List<Visit> visits;
```

And in the visit entity:

```java
@ManyToOne
@JoinColumn(name = "patient_id")
private Patient patient;
```

This represents:

```text
Patient 1 ───────── * Visit
```

---

## 🧪 Testing

The APIs can be tested using **Postman**.

Typical testing scenarios:

1. Register a new patient
2. Register the same Aadhaar number
3. Search for a patient
4. Create a visit
5. Try creating another active visit
6. Complete the existing visit
7. Create a new visit
8. Retrieve visit history

---

## ▶️ How to Run

### 1. Clone the repository

```bash
git clone <your-github-repository-url>
```

### 2. Open the project

Open the project in:

* IntelliJ IDEA
* Eclipse
* VS Code

### 3. Configure MySQL

Create a database:

```sql
CREATE DATABASE hospital_db;
```

Update:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hospital_db
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

### 4. Build the project

```bash
mvn clean install
```

### 5. Run the application

```bash
mvn spring-boot:run
```

The application will start on the configured server port.

---

## 💡 Key Learning Outcomes

Through this project, I gained practical experience in:

* Building RESTful APIs using Spring Boot
* Implementing layered architecture
* Designing relational database relationships
* Using JPA and Hibernate
* Implementing business rules in the service layer
* Using dependency injection
* Handling database constraints
* Working with entity relationships
* Testing APIs using Postman
* Debugging Spring Boot and SQL errors
* Managing source code using Git and GitHub

---

## 🎯 Interview Explanation

### Short Version

> "I developed a Hospital Workflow Management System using Java and Spring Boot. It provides REST APIs for managing patients and their hospital visits. I used a layered architecture with Controller, Service, Repository, and Entity layers. Spring Data JPA and Hibernate are used for database interaction with MySQL. One important business rule I implemented is checking the Visit table for an existing ACTIVE visit before creating a new visit, which prevents duplicate active visits for the same patient. I also implemented unique constraints to prevent duplicate patient registration."

### Main Interview Flow

```text
Request
  ↓
Controller
  ↓
Service
  ↓
Business Validation
  ↓
Repository
  ↓
JPA/Hibernate
  ↓
MySQL
  ↓
Response
```

---

## 👨‍💻 Author

**C KISHORE**

Java | Spring Boot | REST API | MySQL | JPA | Hibernate

---

## ⭐ Project Highlights

* RESTful backend application
* Layered architecture
* Spring Boot
* Spring Data JPA
* Hibernate ORM
* MySQL database
* Patient and visit management
* Active visit validation
* Duplicate patient prevention
* Git/GitHub version control
