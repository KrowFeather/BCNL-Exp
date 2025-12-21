package com.krowfeather.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "registration")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Registration {
    // 选课记录ID - 主键
    // 使用数据库自增策略生成主键值
    @Id  // 标识这是主键字段
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 指定主键生成策略为数据库自增
    private Long id;

    // 关联的学生实体
    // 多对一关系：多个选课记录对应一个学生
    // 使用延迟加载优化性能
    // 通过"uid"外键列关联User表
    @ManyToOne(fetch = FetchType.LAZY)  // 定义多对一关系，使用延迟加载
    @JoinColumn(name = "uid", nullable = false)  // 指定外键列名为"uid"，不允许为空
    private User user;

    // 关联的课程实体
    // 多对一关系：多个选课记录对应一门课程
    // 使用延迟加载优化性能
    // 通过"cid"外键列关联Course表
    @ManyToOne(fetch = FetchType.LAZY)  // 定义多对一关系，使用延迟加载
    @JoinColumn(name = "cid", nullable = false)  // 指定外键列名为"cid"，不允许为空
    private Course course;
}