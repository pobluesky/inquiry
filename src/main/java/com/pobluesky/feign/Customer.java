package com.pobluesky.feign;

import com.pobluesky.global.security.UserRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
    private Long userId;
    protected String name;
    protected String email;
    protected String password;
    protected String phone;
    protected Boolean isActivated;
    protected UserRole role;
    private String customerCode;
    private String customerName;
    private String securityRole;

}
