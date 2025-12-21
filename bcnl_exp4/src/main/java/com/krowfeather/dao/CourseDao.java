package com.krowfeather.dao;

import com.krowfeather.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Course数据访问接口
// 继承JpaRepository，提供基本的CRUD操作
@Repository  // 标识这是一个数据访问层的Bean
public interface CourseDao extends JpaRepository<Course, String> {

    // 自定义查询方法1：根据课程名称查找课程
    List<Course> findByName(String name);

    // 自定义查询方法2：根据学分范围查找课程
    List<Course> findByCreditBetween(Float minCredit, Float maxCredit);

    // 自定义JPQL查询：根据课程名称模糊查询
    @Query("SELECT c FROM Course c WHERE c.name LIKE %:name%")
    List<Course> findCoursesByNameContaining(@Param("name") String name);

    // 自定义更新操作：更新课程学分
    @Modifying
    @Transactional
    @Query("UPDATE Course c SET c.credit = :credit WHERE c.id = :id")
    int updateCourseCredit(@Param("id") String id, @Param("credit") Float credit);
}

