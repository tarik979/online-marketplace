package com.online_marketplace.model;


import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class LocalUser implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column(name = "email", unique =  true, nullable = false, length = 320)
    private String email;

    @Column(name = "password", nullable = false, length = 3000)
    @JsonIgnore
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at", length = 100)
    private Date createAt;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "seller")
    @JsonIgnore
    private Set<OrderDetails> soldOrders;

    @OneToMany(mappedBy = "buyer")
    @JsonIgnore
    private Set<OrderDetails> boughtOrders;
    

   /*  @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getName().name())); // Assuming role name is used directly
    }*/


    public String getUsername() {
        return email;
    }

//     @Override
//     public String getUsername() {
//        return email;
//  }

    // @Override
    // public boolean isAccountNonExpired() {
    //     return UserDetails.super.isAccountNonExpired();
    // }

    // @Override
    // public boolean isAccountNonLocked() {
    //     return UserDetails.super.isAccountNonLocked();
    // }

    // @Override
    // public boolean isCredentialsNonExpired() {
    //     return UserDetails.super.isCredentialsNonExpired();
    // }

    // @Override
    // public boolean isEnabled() {
    //     return UserDetails.super.isEnabled();
    // }
}
