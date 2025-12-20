package com.krowfeather.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Course实体类 - 映射数据库中的course表
// 代表一门课程的信息

// 标识这是一个JPA实体类
@Entity
@Table(name = "course")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    // 课程ID - 主键
    @Id
    private String id;

    // 课程名称
    // 对应数据库表中的"name"列
    @Column(name = "name")
    private String name;

    // 课程学分
    // 对应数据库表中的"credit"列
    @Column(name = "credit")
    private Float credit;
}

