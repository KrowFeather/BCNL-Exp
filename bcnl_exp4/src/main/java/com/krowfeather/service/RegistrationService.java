package com.krowfeather.service;

import com.krowfeather.entity.Course;
import com.krowfeather.entity.Registration;
import com.krowfeather.entity.User;

import java.util.List;

public interface RegistrationService {
    // 注册学生
    void register(User user, Course course);

    Registration findById(Long id);

    List<Registration> findAll();

    void delete(Registration registration);
}
