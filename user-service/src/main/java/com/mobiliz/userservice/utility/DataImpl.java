package com.mobiliz.userservice.utility;

import com.mobiliz.userservice.entity.Company;
import com.mobiliz.userservice.entity.User;
import com.mobiliz.userservice.entity.enums.Role;
import com.mobiliz.userservice.repository.CompanyRepository;
import com.mobiliz.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class DataImpl {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    //@PostConstruct
    public void initData(){
        createCompany();
        createUser();

    }

        public void createCompany(){
            Company company = Company.builder()
                    .companyName("X")
                    .build();
            companyRepository.save(company);
        }

        public void createUser(){
            User user= User.builder()
                    .name("admin")
                    .password("12345")
                    .email("admin@admin")
                    .lastName("admin")
                    .role(Role.COMPANYADMIN)
                    .build();
            userRepository.save(user);
            user.setCompany(companyRepository.findById(1L).get());
            userRepository.save(user);

        }




}
