package com.krowfeather.dao;

import com.krowfeather.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RegistrationDao extends JpaRepository<Registration, Long> {

    // 自定义查询方法1：根据学生ID查找选课记录
    @Query("SELECT r FROM Registration r WHERE r.user.id = :userId")
    List<Registration> findByUserId(@Param("userId") String userId);

    // 自定义查询方法2：根据课程ID查找选课记录
    @Query("SELECT r FROM Registration r WHERE r.course.id = :courseId")
    List<Registration> findByCourseId(@Param("courseId") String courseId);

    // 自定义删除操作：根据用户ID删除选课记录
    @Modifying
    @Transactional
    @Query("DELETE FROM Registration r WHERE r.user.id = :userId")
    int deleteByUserId(@Param("userId") String userId);

    // 自定义统计查询：统计某门课程的选课人数
    @Query("SELECT COUNT(r) FROM Registration r WHERE r.course.id = :courseId")
    int countByCourseId(@Param("courseId") String courseId);
}
