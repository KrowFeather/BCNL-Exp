package com.krowfeather.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 使用Lombok注解自动生成getter、setter、toString等方法
@Data
// 使用Lombok注解自动生成全参数构造函数
@AllArgsConstructor
// 使用Lombok注解自动生成无参构造函数
@NoArgsConstructor
public class Course {
    // 课程ID
    private String id;
    // 课程名称
    private String name;
    // 课程学分
    private Float credit;

    // 重写toString方法，用于输出课程信息
    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", credit=" + credit +
                '}';
    }
}
