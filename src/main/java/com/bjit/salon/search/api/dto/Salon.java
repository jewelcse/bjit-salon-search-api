package com.bjit.salon.search.api.dto;

import lombok.*;

import java.time.LocalTime;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Salon {

    private long id;
    private String name;
    private String description;
    private String address;
    private long userId;
    private double reviews;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private String contractNumber;
}
