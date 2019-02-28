package com.epam.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TokenModel {
    private LocalDateTime expTime;
    private String token;
    private Integer userId;
}
