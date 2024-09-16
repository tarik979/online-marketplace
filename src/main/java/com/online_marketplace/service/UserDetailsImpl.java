package com.online_marketplace.service;

import java.util.Date;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.online_marketplace.model.LocalUser;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String email;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    @SuppressWarnings("unused")
    private Date createdAt;

    public UserDetailsImpl(Long id, String email, String password, Date createdAt,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(LocalUser user) {
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(user.getRole().getName().name()));
        return new UserDetailsImpl(user.getUserId(), user.getEmail(), user.getPassword(), user.getCreateAt(), authorities);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

}
