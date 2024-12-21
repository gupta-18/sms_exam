package com.mkipts.bank.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StateResponseDto {
    private Integer id;
    private String stateName;
}