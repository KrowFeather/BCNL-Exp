package com.krowfeather.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

// Student实体类 - 映射数据库中的student表

@Entity  // 标识这是一个JPA实体类
@Table(name = "student")  // 指定映射的数据库表名为"student"
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    // 学生ID - 主键
    // 使用数据库自增策略生成主键值
    @Id
    private String id;

    // 学生姓名
    // 对应数据库表中的"name"列
    @Column(name = "name")
    private String name;

    // 学生性别
    // 对应数据库表中的"gender"列
    @Column(name = "gender")
    private Integer gender;

    // 学生年龄
    // 对应数据库表中的"age"列
    @Column(name = "age")
    private Integer age;

    // 学生生日
    // 对应数据库表中的"birthday"列
    @Column(name = "birthday")
    private Date birthday;

    // 学生密码
    // 对应数据库表中的"password"列，不允许为空
    @Column(name = "password", nullable = false)
    private String password;
}
