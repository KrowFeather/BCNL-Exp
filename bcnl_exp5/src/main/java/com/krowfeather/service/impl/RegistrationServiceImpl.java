package com.krowfeather.service.impl;

import com.krowfeather.dao.RegistrationDao;
import com.krowfeather.entity.Course;
import com.krowfeather.entity.Registration;
import com.krowfeather.entity.Student;
import com.krowfeather.service.RegistrationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Resource
    private RegistrationDao registrationDao;

    @Override
    public void register(Student student, Course course) {
        // 创建注册记录
        Registration registration = new Registration();
        registration.setStudent(student);
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
}
