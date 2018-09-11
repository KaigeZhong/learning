package com.time;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class Time {
    public static void main(String[] args) {
        System.out.println(Instant.now());

        LocalTime now = LocalTime.now();
        LocalTime of = LocalTime.of(0, 0, 0);
        Duration between = Duration.between(of, now);
        System.out.println(between.toMinutes());

        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDateTime ofDateTime = LocalDateTime.of(2018, 7, 20, 16, 30, 10);
        Duration betweenDateTime = Duration.between(ofDateTime, nowDateTime);
        System.out.println(betweenDateTime.toDays());

        LocalDate nowDate = LocalDate.now();
        LocalDate ofDate = LocalDate.of(2018, 6, 21);
        long days = ChronoUnit.DAYS.between(ofDate, nowDate);
        System.out.println(days);
    }
}