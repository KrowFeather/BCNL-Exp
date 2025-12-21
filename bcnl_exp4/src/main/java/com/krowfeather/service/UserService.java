package com.krowfeather.service;

import com.krowfeather.entity.User;

import java.util.List;

/**
 * 用户服务接口
 * 提供用户相关的业务逻辑方法
 */
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

    // 用户注册
    boolean register(User user);
    
    // 用户登录
    boolean login(User user);
    
    // 根据姓名查找用户
    List<User> findByName(String name);
    
    // 根据性别和角色查找用户
    List<User> findByGenderAndRole(Integer gender, Integer role);
    
    // 根据姓名模糊查询用户
    List<User> findUsersByNameContaining(String name);
    
    // 更新用户角色
    int updateUserRole(String id, Integer role);
}
