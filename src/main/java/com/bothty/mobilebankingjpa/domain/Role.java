package com.bothty.mobilebankingjpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Setter
@Getter
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String role;

    @ManyToMany(mappedBy = "role")
    private List<User> user;

    @Override
    public String getAuthority() {
        return "ROLE_" + role;
    }
}
