package com.pobluesky.feign;

import com.pobluesky.global.security.UserRole;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Manager {

    private Long userId;
    private String name;
    private UserRole role;

}
