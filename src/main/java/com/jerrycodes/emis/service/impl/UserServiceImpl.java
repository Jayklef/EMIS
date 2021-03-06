package com.jerrycodes.emis.service.impl;

import com.jerrycodes.emis.entity.User;
import com.jerrycodes.emis.model.UserModel;
import com.jerrycodes.emis.service.UserRepository;
import com.jerrycodes.emis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public User saveUser(UserModel userModel) {
        User user = new User();
        user.setUsername(user.getUsername());
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
