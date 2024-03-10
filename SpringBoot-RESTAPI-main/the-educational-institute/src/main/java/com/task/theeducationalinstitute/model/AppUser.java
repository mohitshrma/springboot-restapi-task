package com.task.theeducationalinstitute.model;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUser {
    @Id
    private String id;
    private String name;
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

}

