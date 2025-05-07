package com.datn.backendHN.dto;

import lombok.Data;

@Data
public class TokenInfoDTO {
    private Integer userId;
    private String accountType;
    private boolean isValid;
    private String message;
} 