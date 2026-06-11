package com.LittleDelhi.LittleDelhiBackend.mapper;

import com.LittleDelhi.LittleDelhiBackend.dto.response.TimeSheetResponse;
import com.LittleDelhi.LittleDelhiBackend.model.TimeSheet;
import org.springframework.stereotype.Component;

@Component
public class TimeSheetMapper {

    private final UserMapper userMapper;

    public TimeSheetMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public TimeSheetResponse toResponse(TimeSheet timeSheet) {
        return TimeSheetResponse.builder()
                .id(timeSheet.getId())
                .user(userMapper.toResponse(timeSheet.getUser()))
                .date(timeSheet.getDate())
                .clockIn(timeSheet.getClockIn())
                .clockOut(timeSheet.getClockOut())
                .hoursWorked(timeSheet.getHoursWorked())
                .build();
    }
}
