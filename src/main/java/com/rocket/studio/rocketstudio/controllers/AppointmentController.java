package com.rocket.studio.rocketstudio.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocket.studio.rocketstudio.AppointmentRequestDto;
import com.rocket.studio.rocketstudio.services.AppointmentService;

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
