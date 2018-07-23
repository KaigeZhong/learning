package com;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

public class Time {
    public static void main(String[] args) {
        System.out.println(Instant.now());
        LocalTime now = LocalTime.now();
        System.out.println(now);
        LocalTime of = LocalTime.of(0, 0, 0);
        Duration between = Duration.between(of, now);
        System.out.println(between.toMinutes());
        System.out.println(Math.ceil(2));
        int a = (int) 3.234D;
        System.out.println(a);
        LocalDate now1 = LocalDate.now();
    }
}