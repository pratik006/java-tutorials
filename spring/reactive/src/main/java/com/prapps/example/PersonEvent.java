package com.prapps.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class PersonEvent {
    private String eventId;
    private Long personId;
    private LocalTime time;
}
