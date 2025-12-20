package com.krowfeather.service.impl;

import com.krowfeather.dao.StudentDao;
import com.krowfeather.entity.Student;
import com.krowfeather.service.StudentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentDao;

    @Override
    public void save(Student student) {
        studentDao.save(student);
    }

    @Override
    public Student findById(String id) {
        return studentDao.findById(id).orElse(null);
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public void update(Student student) {
        studentDao.save(student);
    }

    @Override
    public void delete(Student student) {
        studentDao.delete(student);
    }

    @Override
    public boolean register(Student student) {
        if (studentDao.findById(student.getId()).isPresent()) {
            return false;
        }else {
            studentDao.save(student);
            return true;
        }
    }

    @Override
    public boolean login(Student student) {
        Student student1 = studentDao.findById(student.getId()).orElse(null);
        if (student1 == null) {
            return false;
        }else {
            return student1.getPassword().equals(student.getPassword());
        }
    }
}
