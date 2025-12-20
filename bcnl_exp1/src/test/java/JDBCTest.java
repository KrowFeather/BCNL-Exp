import com.krowfeather.utils.JDBCUtil;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.time.LocalDate;

public class JDBCTest {
    // 查询操作
    @Test
    public void selectData() {
        // 获取数据库连接
        Connection connection = JDBCUtil.getConnection();
        // 定义查询SQL语句
        String sql = "SELECT * FROM student";

        try {
            // 创建预编译语句对象
            PreparedStatement pstmt = connection.prepareStatement(sql);
            // 执行查询操作获取结果集
            ResultSet rs = pstmt.executeQuery();
            // 输出查询结果提示
            System.out.println("查询结果:");
            // 遍历结果集中的每一行数据
            while (rs.next()) {
                // 假设users表有id, name, email字段
                // 从结果集中获取id字段值
                String id = rs.getString("id");
                // 从结果集中获取name字段值
                String name = rs.getString("name");
                // 从结果集中获取age字段值
                int age = rs.getInt("age");
                // 从结果集中获取birthday字段值
                Date birthday = rs.getDate("birthday");
                // 输出查询到的记录信息
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Birthday: " + birthday);
            }
            // 关闭数据库连接、语句和结果集
            JDBCUtil.close(connection, pstmt, rs);
        } catch (SQLException e) {
            // 捕获并输出SQL异常信息
            System.err.println("查询数据失败: " + e.getMessage());
        } finally {
            // 最终处理块（当前为空）
        }
    }

    // 插入操作
    @Test
    public void insertData() {
        // 获取数据库连接
        Connection connection = JDBCUtil.getConnection();
        // 定义要插入的学生信息
        String id = "20221145140";
        String name = "张三";
        int gender = 1;
        int age = 20;
        // 创建生日日期对象
        LocalDate localDate = LocalDate.of(2005, 1, 21);
        Date birthday = Date.valueOf(localDate);

        // 定义插入SQL语句，使用占位符
        String sql = "INSERT INTO student VALUES (?, ?, ?, ?, ?)";

        // 使用try-with-resources自动关闭资源
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // 设置第一个占位符的值为学生ID
            pstmt.setString(1, id);
            // 设置第二个占位符的值为学生姓名
            pstmt.setString(2, name);
            // 设置第四个占位符的值为性别
            pstmt.setInt(3, gender);
            // 设置第五个占位符的值为年龄
            pstmt.setInt(4, age);
            // 设置第六个占位符的值为生日（注意：此处可能会出现索引错误）
            pstmt.setDate(5, birthday);

            // 执行插入操作并获取影响行数
            int rowsAffected = pstmt.executeUpdate();
            // 判断是否插入成功
            if (rowsAffected > 0) {
                // 输出插入成功的消息和影响行数
                System.out.println("插入数据成功，影响行数: " + rowsAffected);
            } else {
                // 输出插入失败的消息
                System.out.println("插入数据失败");
            }
            // 关闭数据库连接和语句
            JDBCUtil.close(connection, pstmt, null);
        } catch (SQLException e) {
            // 捕获并输出SQL异常信息
            System.err.println("插入数据异常: " + e.getMessage());
        }
    }

    // 更新操作
    @Test
    public void updateData() {
        // 获取数据库连接
        Connection connection = JDBCUtil.getConnection();
        // 定义要更新的学生ID和新姓名
        String id = "20221145140";
        String name = "李四";

        // 定义更新SQL语句，使用占位符
        String sql = "UPDATE student SET name = ? WHERE id = ?";

        // 使用try-with-resources自动关闭资源
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // 设置第一个占位符的值为新姓名
            pstmt.setString(1, name);
            // 设置第二个占位符的值为学生ID
            pstmt.setString(2, id);

            // 执行更新操作并获取影响行数
            int rowsAffected = pstmt.executeUpdate();
            // 判断是否更新成功
            if (rowsAffected > 0) {
                // 输出更新成功的消息和影响行数
                System.out.println("更新数据成功，影响行数: " + rowsAffected);
            } else {
                // 输出更新失败的消息
                System.out.println("更新数据失败，未找到ID为 " + id + " 的记录");
            }
            // 关闭数据库连接和语句
            JDBCUtil.close(connection, pstmt, null);
        } catch (SQLException e) {
            // 捕获并输出SQL异常信息
            System.err.println("更新数据异常: " + e.getMessage());
        }
    }

    // 删除操作
    @Test
    public void deleteData() {
        // 获取数据库连接
        Connection connection = JDBCUtil.getConnection();
        // 定义要删除的学生ID
        String id = "20221145140";
        // 定义删除SQL语句，使用占位符
        String sql = "DELETE FROM student WHERE id = ?";

        // 使用try-with-resources自动关闭资源
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // 设置第一个占位符的值为学生ID
            pstmt.setString(1, id);
            // 执行删除操作并获取影响行数
            int rowsAffected = pstmt.executeUpdate();
            // 判断是否删除成功
            if (rowsAffected > 0) {
                // 输出删除成功的消息和影响行数
                System.out.println("删除数据成功，影响行数: " + rowsAffected);
            } else {
                // 输出删除失败的消息
                System.out.println("删除数据失败，未找到ID为 " + id + " 的记录");
            }
            // 关闭数据库连接和语句
            JDBCUtil.close(connection, pstmt, null);
        } catch (SQLException e) {
            // 捕获并输出SQL异常信息
            System.err.println("删除数据异常: " + e.getMessage());
        }
    }

    // 主函数，用于直接运行测试
    public static void main(String[] args) {
        // 创建JDBCTest实例
        JDBCTest jdbcTest = new JDBCTest();
        // 执行查询数据测试
//        jdbcTest.selectData();
//        jdbcTest.insertData();
//        jdbcTest.updateData();
        jdbcTest.deleteData();
    }
}
