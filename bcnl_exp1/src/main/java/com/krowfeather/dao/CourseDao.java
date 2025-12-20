package com.krowfeather.dao;

import com.krowfeather.entity.Course;
import com.krowfeather.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseDao implements IBaseDao<Course, String> {
    @Override
    public Optional<Course> findById(String id) {
        // 获取数据库连接
        Connection connection = JDBCUtil.getConnection();
        // 定义根据ID查询的SQL语句
        String sql = "SELECT * FROM course WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // 设置参数
            pstmt.setString(1, id);
            // 执行查询
            ResultSet rs = pstmt.executeQuery();

            // 如果查询到结果，封装成Course对象
            if (rs.next()) {
                Course course = new Course();
                course.setId(rs.getString("id"));
                course.setName(rs.getString("name"));
                course.setCredit(rs.getFloat("credit"));
                return Optional.of(course);
            }
        } catch (SQLException e) {
            System.err.println("根据ID查询课程失败: " + e.getMessage());
        } finally {
            // 关闭连接
            JDBCUtil.close(connection, null, null);
        }

        // 没有找到对应记录，返回空的Optional
        return Optional.empty();
    }

    @Override
    public List<Course> findAll() {
        // 创建存储所有课程的列表
        List<Course> courses = new ArrayList<>();
        // 获取数据库连接
        Connection connection = JDBCUtil.getConnection();
        // 定义查询所有记录的SQL语句
        String sql = "SELECT * FROM course";

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // 遍历结果集，封装每个Course对象
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getString("id"));
                course.setName(rs.getString("name"));
                course.setCredit(rs.getFloat("credit"));
                courses.add(course);
            }
        } catch (SQLException e) {
            System.err.println("查询所有课程失败: " + e.getMessage());
        } finally {
            // 关闭连接
            JDBCUtil.close(connection, null, null);
        }

        // 返回课程列表
        return courses;
    }

    @Override
    public void delete(Course entity) {
        // 获取数据库连接
        Connection connection = JDBCUtil.getConnection();
        // 定义删除SQL语句
        String sql = "DELETE FROM course WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // 设置参数
            pstmt.setString(1, entity.getId());
            // 执行删除操作
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("删除课程失败: " + e.getMessage());
        } finally {
            // 关闭连接
            JDBCUtil.close(connection, null, null);
        }
    }

    @Override
    public void update(Course entity) {
        // 获取数据库连接
        Connection connection = JDBCUtil.getConnection();
        // 定义更新SQL语句
        String sql = "UPDATE course SET name = ?, credit = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // 设置参数
            pstmt.setString(1, entity.getName());
            pstmt.setFloat(2, entity.getCredit());
            pstmt.setString(3, entity.getId());
            // 执行更新操作
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("更新课程失败: " + e.getMessage());
        } finally {
            // 关闭连接
            JDBCUtil.close(connection, null, null);
        }
    }

    @Override
    public void save(Course entity) {
        // 获取数据库连接
        Connection connection = JDBCUtil.getConnection();
        // 定义插入SQL语句
        String sql = "INSERT INTO course (id, name, credit) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // 设置参数
            pstmt.setString(1, entity.getId());
            pstmt.setString(2, entity.getName());
            pstmt.setFloat(3, entity.getCredit());
            // 执行插入操作
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("保存课程失败: " + e.getMessage());
        } finally {
            // 关闭连接
            JDBCUtil.close(connection, null, null);
        }
    }
}
