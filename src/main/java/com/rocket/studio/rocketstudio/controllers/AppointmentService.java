package com.rocket.studio.rocketstudio.controllers;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/request")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> requestAppointment(@RequestBody AppointmentRequestDto requestDto, Principal principal) {
        try {
            appointmentService.requestAppointment(requestDto, principal.getName());
            return ResponseEntity.ok("Appointment requested successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to request appointment");
        }
    }
}

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

