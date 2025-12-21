package com.krowfeather.service.impl;

import com.krowfeather.dao.RegistrationDao;
import com.krowfeather.entity.Course;
import com.krowfeather.entity.Registration;
import com.krowfeather.entity.User;
import com.krowfeather.service.RegistrationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 选课服务实现类
 * 实现选课相关的业务逻辑
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Resource
    private RegistrationDao registrationDao;

    @Override
    public void register(User user, Course course) {
        // 创建注册记录
        Registration registration = new Registration();
        registration.setUser(user);
        registration.setCourse(course);

        // 保存注册记录
        registrationDao.save(registration);
    }

    @Override
    public Registration findById(Long id) {
        return registrationDao.findById(id).orElse(null);
    }

    @Override
    public List<Registration> findAll() {
        return registrationDao.findAll();
    }

    @Override
    public void delete(Registration registration) {
        registrationDao.delete(registration);
    }

    @Override
    public List<Registration> findByUserId(String userId) {
        return registrationDao.findByUserId(userId);
    }

    @Override
    public List<Registration> findByCourseId(String courseId) {
        return registrationDao.findByCourseId(courseId);
    }

    @Override
    public int deleteByUserId(String userId) {
        return registrationDao.deleteByUserId(userId);
    }

    @Override
    public int countByCourseId(String courseId) {
        return registrationDao.countByCourseId(courseId);
    }
}
