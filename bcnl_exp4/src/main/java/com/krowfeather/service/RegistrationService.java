package com.krowfeather.service;

import com.krowfeather.entity.Course;
import com.krowfeather.entity.Registration;
import com.krowfeather.entity.User;

import java.util.List;

/**
 * 选课服务接口
 * 提供选课相关的业务逻辑方法
 */
public interface RegistrationService {
    // 注册选课
    void register(User user, Course course);

    // 根据ID查找选课记录
    Registration findById(Long id);

    // 查找所有选课记录
    List<Registration> findAll();

    // 删除选课记录
    void delete(Registration registration);
    
    // 根据用户ID查找选课记录
    List<Registration> findByUserId(String userId);
    
    // 根据课程ID查找选课记录
    List<Registration> findByCourseId(String courseId);
    
    // 根据用户ID删除选课记录
    int deleteByUserId(String userId);
    
    // 统计某门课程的选课人数
    int countByCourseId(String courseId);
}
