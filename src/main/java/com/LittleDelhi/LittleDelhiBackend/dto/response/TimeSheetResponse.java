package com.LittleDelhi.LittleDelhiBackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeSheetResponse {
    private Long id;
    private UserResponse user;
    private LocalDate date;
    private LocalTime clockIn;
    private LocalTime clockOut;
    private double hoursWorked;
}
