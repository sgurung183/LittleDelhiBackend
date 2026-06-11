package com.LittleDelhi.LittleDelhiBackend.controller;

import com.LittleDelhi.LittleDelhiBackend.dto.request.ClockInRequest;
import com.LittleDelhi.LittleDelhiBackend.dto.response.TimeSheetResponse;
import com.LittleDelhi.LittleDelhiBackend.service.TimeSheetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timesheets")
public class TimeSheetController {

    private final TimeSheetService timeSheetService;

    public TimeSheetController(TimeSheetService timeSheetService) {
        this.timeSheetService = timeSheetService;
    }

    @PostMapping("/clock-in")
    public ResponseEntity<TimeSheetResponse> clockIn(@RequestBody ClockInRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(timeSheetService.clockIn(request));
    }

    @PutMapping("/clock-out/{id}")
    public ResponseEntity<TimeSheetResponse> clockOut(@PathVariable Long id) {
        return ResponseEntity.ok(timeSheetService.clockOut(id));
    }

    @GetMapping
    public ResponseEntity<List<TimeSheetResponse>> getAllTimesheets() {
        return ResponseEntity.ok(timeSheetService.getAllTimesheets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeSheetResponse> getTimesheetById(@PathVariable Long id) {
        return ResponseEntity.ok(timeSheetService.getTimesheetById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TimeSheetResponse>> getTimesheetsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(timeSheetService.getTimesheetsByUser(userId));
    }
}
