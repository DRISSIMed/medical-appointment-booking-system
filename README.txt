1- Pour lancer l'application :
      mvn install -DskipTests
      run MedicalApplication
Note : Vérifier que vous avez JAVA 21

2- Tech Stack :
   - Backend: Java 21, Spring Boot 4.0.3, Spring Data JPA, MapStruct, Lombok
   - Database: H2 in-memory (auto-seeded on startup)

3- Access :
   - API available at http://localhost:8080
   - H2 Console at http://localhost:8080/h2-console

4- API Endpoints :
    - GET | `/api/v1/doctors` | List all doctors
    - GET | `/api/v1/doctors?specialty=CARDIOLOGY` | Filter by specialty
    - GET | `/api/v1/doctors/{id}` | Get doctor by ID
    - GET | `/api/v1/appointments/doctors/{id}/slots` | Get available slots
    - POST | `/api/v1/appointments/book` | Book a slot

5- Architecture Decisions :
   - Records for DTOs: Immutable, concise, no boilerplate
   - MapStruct: Compile-time safe mapping, no reflection overhead
   - ControllerAdvice: Centralized exception handling with structured error responses

