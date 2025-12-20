package com.krowfeather.service;

import com.krowfeather.entity.Course;

import java.util.List;

public interface CourseService {
    // 保存课程
    void save(Course course);

    // 根据ID查找课程
    Course findById(String id);

    // 查找所有课程
    List<Course> findAll();

    // 更新课程
    void update(Course course);

    // 删除课程
    void delete(Course course);
}
