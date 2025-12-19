import com.krowfeather.utils.JDBCUtil;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class JDBCTest {
    // 查询操作
    @Test
    public void selectData() {
        Connection connection = JDBCUtil.getConnection();
        String sql = "SELECT * FROM student";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("查询结果:");
            while (rs.next()) {
                // 假设users表有id, name, email字段
                String email = rs.getString("id");
                String name = rs.getString("name");
                int id = rs.getInt("age");
                Date birthday = rs.getDate("birthday");
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + id + ", Birthday: " + birthday);
            }
            JDBCUtil.close(connection, pstmt, rs);
        } catch (SQLException e) {
            System.err.println("查询数据失败: " + e.getMessage());
        } finally {
        }
    }

    // 插入操作
    @Test
    public void insertData() {
        Connection connection = JDBCUtil.getConnection();
        String id = "20221145140";
        String name = "张三";
        String email = "zhangsan@example.com";
        int gender = 1;
        int age = 20;
        Date birthday = new Date(2005, 1, 21);

        String sql = "INSERT INTO student VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.setInt(4, gender);
            pstmt.setInt(5, age);
            pstmt.setDate(6, birthday);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("插入数据成功，影响行数: " + rowsAffected);
            } else {
                System.out.println("插入数据失败");
            }
            JDBCUtil.close(connection, pstmt, null);
        } catch (SQLException e) {
            System.err.println("插入数据异常: " + e.getMessage());
        }
    }

    // 更新操作
    @Test
    public void updateData() {
        Connection connection = JDBCUtil.getConnection();
        String id = "20221145140";
        String name = "李四";

        String sql = "UPDATE student SET name = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("更新数据成功，影响行数: " + rowsAffected);
            } else {
                System.out.println("更新数据失败，未找到ID为 " + id + " 的记录");
            }
            JDBCUtil.close(connection, pstmt, null);
        } catch (SQLException e) {
            System.err.println("更新数据异常: " + e.getMessage());
        }
    }

    // 删除操作
    @Test
    public void deleteData() {
        Connection connection = JDBCUtil.getConnection();
        String id = "20221145140";
        String sql = "DELETE FROM student WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("删除数据成功，影响行数: " + rowsAffected);
            } else {
                System.out.println("删除数据失败，未找到ID为 " + id + " 的记录");
            }
            JDBCUtil.close(connection, pstmt, null);
        } catch (SQLException e) {
            System.err.println("删除数据异常: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        JDBCTest jdbcTest = new JDBCTest();
        jdbcTest.selectData();
//        jdbcTest.insertData();
//        jdbcTest.updateData();
//        jdbcTest.deleteData();
    }
}
