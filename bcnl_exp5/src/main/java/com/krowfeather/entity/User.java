package com.krowfeather.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

// User实体类 - 映射数据库中的user表

@Entity  // 标识这是一个JPA实体类
@Table(name = "user")  // 指定映射的数据库表名为"user"
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    // 用户ID - 主键
    // 使用数据库自增策略生成主键值
    @Id
    private String id;

    // 用户姓名
    // 对应数据库表中的"name"列
    @Column(name = "name")
    private String name;

    // 用户性别
    // 对应数据库表中的"gender"列
    @Column(name = "gender")
    private Integer gender;

    // 用户生日
    // 对应数据库表中的"birthday"列
    @Column(name = "birthday")
    private Date birthday;

    // 用户密码
    // 对应数据库表中的"password"列，不允许为空
    @Column(name = "password", nullable = false)
    private String password;

    // 用户角色
    // 对应数据库表中的"role"列，不允许为空，默认值为1
    @Column(name = "role", nullable = false, columnDefinition = "int default 1")
    private Integer role;
}
