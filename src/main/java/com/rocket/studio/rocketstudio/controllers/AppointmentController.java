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
