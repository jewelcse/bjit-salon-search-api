package com.bjit.salon.search.api.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class StaffActivity {

    private long staffId;
    private LocalDate workingDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String workingStatus;
    private Long consumerId;
    private Long reservationId;
}
