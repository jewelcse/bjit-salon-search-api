package com.bjit.salon.search.api.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Catalog {
    private long id;
    private String name;
    private String description;
    private int approximateTimeForCompletion;
    private double payableAmount;
}
