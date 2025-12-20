package com.krowfeather.service;

import com.krowfeather.entity.Student;

import java.util.List;

public interface StudentService {
    // 保存学生
    void save(Student student);

    // 根据ID查找学生
    Student findById(String id);

    // 查找所有学生
    List<Student> findAll();

    // 更新学生
    void update(Student student);

    // 删除学生
    void delete(Student student);

    boolean register(Student student);
    boolean login(Student student);
}
