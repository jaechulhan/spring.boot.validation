package net.prolancer.validation.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.prolancer.validation.entity.User;
import net.prolancer.validation.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public User createUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        int result = userMapper.insertUser(user);
        if (result > 0) {
            return userMapper.selectUserById(user);
        }
        return null;
    }
}
