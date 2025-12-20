package com.krowfeather.dao;

import com.krowfeather.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Course数据访问接口
// 继承JpaRepository，提供基本的CRUD操作
@Repository  // 标识这是一个数据访问层的Bean
public interface CourseDao extends JpaRepository<Course, String> {
}
