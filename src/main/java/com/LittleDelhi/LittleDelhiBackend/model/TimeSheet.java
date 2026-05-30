package com.LittleDelhi.LittleDelhiBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //one user has many timesheet entries
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate date;
    private LocalTime clockIn;
    private LocalTime clockOut;

    private double hoursWorked;

}
