package com.krowfeather.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

// 使用Lombok注解自动生成getter、setter、toString等方法
@Data
// 使用Lombok注解自动生成全参数构造函数
@AllArgsConstructor
// 使用Lombok注解自动生成无参构造函数
@NoArgsConstructor
public class Student {
    // 学生学号
    private String id;
    // 学生姓名
    private String name;
    // 学生性别（0表示女，1表示男）
    private int gender;
    // 学生年龄
    private int age;
    // 学生生日
    private Date birthday;

    // 重写toString方法，用于输出学生信息
    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}

