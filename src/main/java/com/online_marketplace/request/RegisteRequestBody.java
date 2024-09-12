package com.online_marketplace.request;

import com.online_marketplace.model.ERole;
import lombok.Getter;

@Getter
public class RegisteRequestBody{
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private ERole role;
}
