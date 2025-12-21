package com.krowfeather.service.impl;

import com.krowfeather.dao.CourseDao;
import com.krowfeather.entity.Course;
import com.krowfeather.service.CourseService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 课程服务实现类
 * 实现课程相关的业务逻辑
 */
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

    @Override
    public List<Course> findByName(String name) {
        return courseDao.findByName(name);
    }

    @Override
    public List<Course> findByCreditBetween(Float minCredit, Float maxCredit) {
        return courseDao.findByCreditBetween(minCredit, maxCredit);
    }

    @Override
    public List<Course> findCoursesByNameContaining(String name) {
        return courseDao.findCoursesByNameContaining(name);
    }

    @Override
    public int updateCourseCredit(String id, Float credit) {
        return courseDao.updateCourseCredit(id, credit);
    }
}
