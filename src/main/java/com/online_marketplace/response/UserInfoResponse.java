package com.online_marketplace.response;

import java.util.Date;
import java.util.List;

public class UserInfoResponse {
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
    private Date createdAt;
    private String jwt;

    public UserInfoResponse(Long id, String username, String email, Date createdAt, List<String> roles, String token) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.roles = roles;
        jwt = token;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

}
