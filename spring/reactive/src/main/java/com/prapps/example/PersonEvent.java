package com.prapps.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalTime;

@AllArgsConstructor
@ToString
@Data
public class PersonEvent {
    private String eventId;
    private LocalTime time;
}
