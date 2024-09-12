package com.online_marketplace.model;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter
@NoArgsConstructor
public class Role implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Role_genrator")
    @SequenceGenerator(name = "Role_generator", sequenceName = "Role_genrator", allocationSize = 1)
   private long id;
   @Enumerated(EnumType.STRING)
   private  ERole name;

   @OneToMany(mappedBy = "role")
   @JsonIgnore
   private Set<LocalUser> users;

   public Role(ERole name){
    this.name = name;
   }


}
