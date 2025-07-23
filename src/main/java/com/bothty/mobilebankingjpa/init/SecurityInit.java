package com.bothty.mobilebankingjpa.init;

import com.bothty.mobilebankingjpa.domain.Role;
import com.bothty.mobilebankingjpa.domain.User;
import com.bothty.mobilebankingjpa.repository.RoleRepository;
import com.bothty.mobilebankingjpa.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SecurityInit {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

//    @PostConstruct
//    void init(){
//        Role admin = new Role();
//        admin.setRole("ADMIN");
//        Role staff = new Role();
//        staff.setRole("STAFF");
//        Role customer = new Role();
//        customer.setRole("CUSTOMER");
//        Role defaultRole = new Role();
//        defaultRole.setRole("USER");
//
//        if(roleRepository.count() == 0){
//            roleRepository.saveAll(List.of(admin, staff, customer, defaultRole))    ;
//        }
//
//        if (userRepository.count() == 0){
//            User userAdmin = new User();
//            userAdmin.setUsername("admin");
//            userAdmin.setPassword(passwordEncoder.encode("pwd@123"));
//            userAdmin.setIsEnable(true);
//            userAdmin.setRole(List.of(defaultRole, admin));
//
//            User userStaff = new User();
//            userStaff.setUsername("staff");
//            userStaff.setPassword(passwordEncoder.encode("pwd@123"));
//            userStaff.setIsEnable(true);
//            userStaff.setRole(List.of(defaultRole, staff));
//
//            User userCustomer = new User();
//            userCustomer.setUsername("customer");
//            userCustomer.setPassword(passwordEncoder.encode("pwd@123"));
//            userCustomer.setIsEnable(true);
//            userCustomer.setRole(List.of(defaultRole, customer));
//
//            userRepository.saveAll(List.of(userAdmin,userStaff,userCustomer));
//        }
//    }
}
