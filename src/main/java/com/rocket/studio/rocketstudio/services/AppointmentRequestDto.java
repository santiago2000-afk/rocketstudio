package com.rocket.studio.rocketstudio.services;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void requestAppointment(AppointmentRequestDto requestDto, String username) {
        // Lógica para crear una cita
        Appointment appointment = new Appointment();
        appointment.setDate(requestDto.getDate());
        appointment.setTime(requestDto.getTime());
        appointment.setPatientUsername(username);
        // Más lógica de negocio...

        appointmentRepository.save(appointment);
    }
}

@Data
public class AppointmentRequestDto {
    private String date;
    private String time;
    // Otros campos necesarios
}

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String time;
    private String patientUsername;
    // Otros campos necesarios
}

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}

