package com.LittleDelhi.LittleDelhiBackend.service;

import com.LittleDelhi.LittleDelhiBackend.dto.request.ClockInRequest;
import com.LittleDelhi.LittleDelhiBackend.dto.response.TimeSheetResponse;
import com.LittleDelhi.LittleDelhiBackend.mapper.TimeSheetMapper;
import com.LittleDelhi.LittleDelhiBackend.model.TimeSheet;
import com.LittleDelhi.LittleDelhiBackend.model.User;
import com.LittleDelhi.LittleDelhiBackend.repository.TimeSheetRepository;
import com.LittleDelhi.LittleDelhiBackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/*
#       [x] clockIn(ClockInRequest) -> TimeSheetResponse
#       [x] clockOut(Long id) -> TimeSheetResponse
#       [x] getTimesheetsByUser(Long userId) -> List<TimeSheetResponse>
#       [x] getAllTimesheets() -> List<TimeSheetResponse>
#       [x] getTimesheetById(Long id) -> TimeSheetResponse
*/
@Service
public class TimeSheetService {

    private final TimeSheetRepository timeSheetRepository;
    private final UserRepository userRepository;
    private final TimeSheetMapper timeSheetMapper;

    public TimeSheetService(TimeSheetRepository timeSheetRepository, UserRepository userRepository, TimeSheetMapper timeSheetMapper) {
        this.timeSheetRepository = timeSheetRepository;
        this.userRepository = userRepository;
        this.timeSheetMapper = timeSheetMapper;
    }

    public TimeSheetResponse clockIn(ClockInRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User does not exist with id: " + request.getUserId()));

        TimeSheet saved = timeSheetRepository.save(
                TimeSheet.builder()
                        .user(user)
                        .date(LocalDate.now())
                        .clockIn(LocalTime.now())
                        .build()
        );

        return timeSheetMapper.toResponse(saved);
    }

    public TimeSheetResponse clockOut(Long id) {
        TimeSheet timeSheet = timeSheetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Timesheet does not exist with id: " + id));

        LocalTime clockOut = LocalTime.now();
        // calculate hours worked by getting the minutes between clockIn and clockOut, then converting to hours
        double hoursWorked = Duration.between(timeSheet.getClockIn(), clockOut).toMinutes() / 60.0;

        timeSheet.setClockOut(clockOut);
        timeSheet.setHoursWorked(hoursWorked);

        return timeSheetMapper.toResponse(timeSheetRepository.save(timeSheet));
    }

    public List<TimeSheetResponse> getTimesheetsByUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User does not exist with id: " + userId);
        }
        return timeSheetRepository.findByUserId(userId).stream()
                .map(timeSheetMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<TimeSheetResponse> getAllTimesheets() {
        return timeSheetRepository.findAll().stream()
                .map(timeSheetMapper::toResponse)
                .collect(Collectors.toList());
    }

    public TimeSheetResponse getTimesheetById(Long id) {
        return timeSheetMapper.toResponse(timeSheetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Timesheet does not exist with id: " + id)));
    }
}
