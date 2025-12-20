package com.krowfeather.service;

import com.krowfeather.entity.User;

import java.util.List;

public interface UserService {
    // 保存用户
    void save(User user);

    // 根据ID查找用户
    User findById(String id);

    // 查找所有用户
    List<User> findAll();

    // 更新用户
    void update(User user);

    // 删除用户
    void delete(User user);

    boolean register(User user);
    boolean login(User user);
}
