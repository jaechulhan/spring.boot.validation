package net.prolancer.validation.service;

import net.prolancer.validation.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User createUser(User user) {
        return user;
    }
}
