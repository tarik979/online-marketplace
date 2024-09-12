package com.online_marketplace.request;

import lombok.Getter;

@Getter
public class LoginRequestBody {
    private String email;
    private String password;
}
