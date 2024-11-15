package com.capstone.project.busbooking;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void saveUser(UserEntity user);
}
