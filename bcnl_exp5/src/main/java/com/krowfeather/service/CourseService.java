package com.krowfeather.service;

import com.krowfeather.entity.Course;

import java.util.List;

/**
 * 课程服务接口
 * 提供课程相关的业务逻辑方法
 */
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
    
    // 根据课程名称查找课程
    List<Course> findByName(String name);
    
    // 根据学分范围查找课程
    List<Course> findByCreditBetween(Float minCredit, Float maxCredit);
    
    // 根据课程名称模糊查询
    List<Course> findCoursesByNameContaining(String name);
    
    // 更新课程学分
    int updateCourseCredit(String id, Float credit);
}
