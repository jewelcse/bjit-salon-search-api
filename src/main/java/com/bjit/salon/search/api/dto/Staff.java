package com.bjit.salon.search.api.dto;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Staff {

    private Long id;
    private String address;
    private boolean isAvailable;
    private String contractNumber;
}
