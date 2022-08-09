package com.jerrycodes.emis.service;

import com.jerrycodes.emis.entity.User;
import com.jerrycodes.emis.model.UserModel;

public interface UserService {

    User saveUser(UserModel userModel);
}
