package com.park.muscle.core.reservation.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import lombok.Getter;

@JsonFormat(shape = Shape.OBJECT)
@Getter
public enum TimeTable {
    AM_7(LocalTime.of(7, 0), true),
    AM_8(LocalTime.of(8, 0), true),
    AM_9(LocalTime.of(9, 0), true),
    AM_10(LocalTime.of(10, 0), true),
    AM_11(LocalTime.of(11, 0), true),
    PM_12(LocalTime.of(12, 0), true),
    PM_13(LocalTime.of(13, 0), true),
    PM_14(LocalTime.of(14, 0), true),
    PM_15(LocalTime.of(15, 0), true),
    PM_16(LocalTime.of(16, 0), true),
    PM_17(LocalTime.of(17, 0), true),
    PM_18(LocalTime.of(18, 0), true),
    PM_19(LocalTime.of(19, 0), true),
    PM_20(LocalTime.of(20, 0), true),
    PM_21(LocalTime.of(21, 0), true),
    PM_22(LocalTime.of(22, 0), true),
    PM_23(LocalTime.of(23, 0), true);

    private final LocalTime time;
    private boolean accessible;

    TimeTable(LocalTime time, boolean accessible) {
        this.time = time;
        this.accessible = accessible;
    }

    public LocalTime getTime() {
        return time;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public void updateAccessible(LocalTime time) {
        Arrays.stream(TimeTable.values())
                .filter(entry -> entry.getTime().equals(time))
                .findFirst()
                .ifPresent(entry -> entry.accessible = false);
    }

    public static HashMap<LocalTime, Boolean> showTimeTable() {
        HashMap<LocalTime, Boolean> timeTable = new LinkedHashMap<>();
        for (TimeTable time : TimeTable.values()) {
            timeTable.put(time.getTime(), time.isAccessible());
        }
        return timeTable;
    }
}
