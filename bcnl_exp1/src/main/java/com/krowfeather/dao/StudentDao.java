package com.krowfeather.dao;

import com.krowfeather.entity.Student;
import com.krowfeather.utils.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDao implements IBaseDao<Student, String> {
    @Override
    public Optional<Student> findById(String id) {
        // 获取数据库连接
        Connection connection = JDBCUtil.getConnection();
        // 定义根据ID查询的SQL语句
        String sql = "SELECT * FROM student WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // 设置参数
            pstmt.setString(1, id);
            // 执行查询
            ResultSet rs = pstmt.executeQuery();

            // 如果查询到结果，封装成Student对象
            if (rs.next()) {
                Student student = new Student();
                student.setId(rs.getString("id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getInt("gender"));
                student.setAge(rs.getInt("age"));
                student.setBirthday(rs.getDate("birthday"));
                return Optional.of(student);
            }
        } catch (SQLException e) {
            System.err.println("根据ID查询学生失败: " + e.getMessage());
        } finally {
            // 关闭连接
            JDBCUtil.close(connection, null, null);
        }

        // 没有找到对应记录，返回空的Optional
        return Optional.empty();
    }

    @Override
    public List<Student> findAll() {
        // 创建存储所有学生的列表
        List<Student> students = new ArrayList<>();
        // 获取数据库连接
        Connection connection = JDBCUtil.getConnection();
        // 定义查询所有记录的SQL语句
        String sql = "SELECT * FROM student";

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // 遍历结果集，封装每个Student对象
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getString("id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getInt("gender"));
                student.setAge(rs.getInt("age"));
                student.setBirthday(rs.getDate("birthday"));
                students.add(student);
            }
        } catch (SQLException e) {
            System.err.println("查询所有学生失败: " + e.getMessage());
        } finally {
            // 关闭连接
            JDBCUtil.close(connection, null, null);
        }

        // 返回学生列表
        return students;
    }

    @Override
    public void delete(Student entity) {
        // 获取数据库连接
        Connection connection = JDBCUtil.getConnection();
        // 定义删除SQL语句
        String sql = "DELETE FROM student WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // 设置参数
            pstmt.setString(1, entity.getId());
            // 执行删除操作
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("删除学生失败: " + e.getMessage());
        } finally {
            // 关闭连接
            JDBCUtil.close(connection, null, null);
        }
    }

    @Override
    public void update(Student entity) {
        // 获取数据库连接
        Connection connection = JDBCUtil.getConnection();
        // 定义更新SQL语句
        String sql = "UPDATE student SET name = ?, gender = ?, age = ?, birthday = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // 设置参数
            pstmt.setString(1, entity.getName());
            pstmt.setInt(2, entity.getGender());
            pstmt.setInt(3, entity.getAge());
            pstmt.setDate(4, (Date) entity.getBirthday());
            pstmt.setString(5, entity.getId());
            // 执行更新操作
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("更新学生失败: " + e.getMessage());
        } finally {
            // 关闭连接
            JDBCUtil.close(connection, null, null);
        }
    }

    @Override
    public void save(Student entity) {
        // 获取数据库连接
        Connection connection = JDBCUtil.getConnection();
        // 定义插入SQL语句
        String sql = "INSERT INTO student (id, name, gender, age, birthday) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // 设置参数
            pstmt.setString(1, entity.getId());
            pstmt.setString(2, entity.getName());
            pstmt.setInt(3, entity.getGender());
            pstmt.setInt(4, entity.getAge());
            pstmt.setDate(5, (Date) entity.getBirthday());
            // 执行插入操作
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("保存学生失败: " + e.getMessage());
        } finally {
            // 关闭连接
            JDBCUtil.close(connection, null, null);
        }
    }
}
