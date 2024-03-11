package com.task.theeducationalinstitute.dto;


import com.task.theeducationalinstitute.utils.AuthStatus;

public record AuthResponseDto(Object token, AuthStatus authStatus) {


}
