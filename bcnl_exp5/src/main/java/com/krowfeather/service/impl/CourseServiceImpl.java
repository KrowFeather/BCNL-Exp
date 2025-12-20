package com.krowfeather.service.impl;

import com.krowfeather.dao.CourseDao;
import com.krowfeather.entity.Course;
import com.krowfeather.service.CourseService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Resource
    private CourseDao courseDao;

    @Override
    public void save(Course course) {
        courseDao.save(course);
    }

    @Override
    public Course findById(String id) {
        return courseDao.findById(id).orElse(null);
    }

    @Override
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    public void update(Course course) {
        courseDao.save(course);
    }

    @Override
    public void delete(Course course) {
        courseDao.delete(course);
    }
}
