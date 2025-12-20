package com.krowfeather.service.impl;

import com.krowfeather.dao.UserDao;
import com.krowfeather.entity.User;
import com.krowfeather.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User findById(String id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void update(User user) {
        userDao.save(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public boolean register(User user) {
        if (userDao.findById(user.getId()).isPresent()) {
            return false;
        }else {
            userDao.save(user);
            return true;
        }
    }

    @Override
    public boolean login(User user) {
        User user1 = userDao.findById(user.getId()).orElse(null);
        if (user1 == null) {
            return false;
        }else {
            return user1.getPassword().equals(user.getPassword());
        }
    }
}
