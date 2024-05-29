package com.tpe.service;

import com.tpe.domain.Role;
import com.tpe.domain.User;
import com.tpe.domain.enums.UserRole;
import com.tpe.dto.UserRequestDTO;
import com.tpe.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleService roleService;

    public void saveUser(UserRequestDTO requestDTO) {

        User newUser = new User();

        newUser.setFirstName(requestDTO.getFirsName());
        newUser.setLastName(requestDTO.getLastName());
        newUser.setUserName(requestDTO.getUserName());

        Role role = roleService.getRoleByType(UserRole.ROLE_STUDENT);

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        newUser.setRoles(roles);

        userRepo.save(newUser);
    }
}
