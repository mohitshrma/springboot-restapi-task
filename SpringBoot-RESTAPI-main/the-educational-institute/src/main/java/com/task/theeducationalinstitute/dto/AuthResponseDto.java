package com.task.theeducationalinstitute.dto;

import jakarta.security.auth.message.AuthStatus;

public record AuthResponseDto(Object token, AuthStatus authStatus) {
    public AuthResponseDto(String jwtToken, com.task.theeducationalinstitute.utils.AuthStatus authStatus) {

    }
}
