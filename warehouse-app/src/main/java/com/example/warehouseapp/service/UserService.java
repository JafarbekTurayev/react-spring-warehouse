package com.example.warehouseapp.service;

import com.example.warehouseapp.entity.Role;
import com.example.warehouseapp.entity.User;
import com.example.warehouseapp.payload.ApiResponse;
import com.example.warehouseapp.payload.UserDto;
import com.example.warehouseapp.repository.RoleRepository;
import com.example.warehouseapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;


    public ApiResponse saveUser(UserDto userDto) {
        User user = new User();
        if (!userRepository.existsByPhoneNumber(userDto.getPhoneNumber())) {

            Optional<Role> optionalRole = roleRepository.findById(userDto.getRoleId());
            user.setRole(optionalRole.get());

            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setPassword(userDto.getPassword());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());

            user.setCode(UUID.randomUUID().toString());

            userRepository.save(user);
            return new ApiResponse("Saved!", true);
        }
        return new ApiResponse("Bunday raqam bor", false);
    }

    public List<User> getALL() {
        List<User> allUser = userRepository.findAll();
        return allUser;
    }

    public User getOneById(UUID id) {
        Optional<User> byId = userRepository.findById(id);
        return byId.orElse(null);
    }

    public User editUser(UUID id, User user) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            User editUser = byId.get();
            editUser.setFirstName(user.getFirstName());
            editUser.setLastName(user.getLastName());
            editUser.setPhoneNumber(user.getPhoneNumber());
            editUser.setCode(user.getCode());
            editUser.setPassword(user.getPassword());
            editUser.setActive(user.isActive());
            return userRepository.save(editUser);

        }
        return null;
    }

    public ApiResponse delete(UUID id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();
            user.setActive(false);
            userRepository.save(user);
            return new ApiResponse("User deactivated", true);
        }
        return new ApiResponse("User not found", false);
    }
}
