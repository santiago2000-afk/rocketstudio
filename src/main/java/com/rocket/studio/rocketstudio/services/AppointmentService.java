package com.rocket.studio.rocketstudio.services;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocket.studio.rocketstudio.Appointment;
import com.rocket.studio.rocketstudio.AppointmentRepository;
import com.rocket.studio.rocketstudio.AppointmentRequestDto;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void requestAppointment(AppointmentRequestDto requestDto, String username) {
        Appointment appointment = new Appointment();
        appointment.setDate(requestDto.getDate());
        appointment.setTime(requestDto.getTime());
        appointment.setPatientUsername(username);

        appointmentRepository.save(appointment);
    }
}

