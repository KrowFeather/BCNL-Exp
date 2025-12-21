package com.krowfeather.dao;

import com.krowfeather.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, String> {

    // 自定义查询方法1：根据姓名查找用户
    List<User> findByName(String name);

    // 自定义查询方法2：根据性别和角色查找用户
    List<User> findByGenderAndRole(Integer gender, Integer role);

    // 自定义JPQL查询：根据姓名模糊查询
    @Query("SELECT u FROM User u WHERE u.name LIKE %:name%")
    List<User> findUsersByNameContaining(@Param("name") String name);

    // 自定义更新操作：更新用户角色
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.role = :role WHERE u.id = :id")
    int updateUserRole(@Param("id") String id, @Param("role") Integer role);
}
