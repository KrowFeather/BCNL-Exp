import com.krowfeather.dao.RegistrationDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

/**
 * 复杂JPQL查询测试类
 * 演示复杂的JPQL查询操作
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ComplexJpqlQueryTest {

    private RegistrationDao registrationDao;

    @BeforeEach
    public void setUp() {
        registrationDao = new RegistrationDao();
    }

    /**
     * 测试查询1：查询每个学生及其已选课程的总学分
     * 使用JOIN、GROUP BY、SUM聚合函数
     */
    @Test
    @Order(1)
    public void testFindStudentsWithTotalCredits() {
        System.out.println("\n=== 复杂查询1：查询每个学生及其已选课程的总学分 ===");
        System.out.println("使用技术：JOIN、GROUP BY、SUM聚合函数、ORDER BY\n");
        
        List<Object[]> results = registrationDao.findStudentsWithTotalCredits();
        
        if (results.isEmpty()) {
            System.out.println("暂无数据");
        } else {
            System.out.println("学生ID\t\t学生姓名\t\t总学分");
            System.out.println("-----------------------------------------------");
            for (Object[] row : results) {
                String studentId = (String) row[0];
                String studentName = (String) row[1];
                Double totalCredits = (Double) row[2];
                System.out.printf("%s\t\t%s\t\t%.2f%n", studentId, studentName, totalCredits);
            }
        }
    }

    /**
     * 测试查询2：查询每门课程的选课人数和平均学生年龄
     * 使用JOIN、GROUP BY、COUNT、AVG聚合函数、HAVING子句
     */
    @Test
    @Order(2)
    public void testFindCoursesWithEnrollmentStats() {
        System.out.println("\n=== 复杂查询2：查询每门课程的选课人数和平均学生年龄 ===");
        System.out.println("使用技术：JOIN、GROUP BY、COUNT、AVG聚合函数、HAVING、WHERE条件\n");
        
        List<Object[]> results = registrationDao.findCoursesWithEnrollmentStats();
        
        if (results.isEmpty()) {
            System.out.println("暂无数据");
        } else {
            System.out.println("课程ID\t课程名称\t\t\t选课人数\t平均年龄");
            System.out.println("-----------------------------------------------------------");
            for (Object[] row : results) {
                String courseId = (String) row[0];
                String courseName = (String) row[1];
                Long enrollmentCount = (Long) row[2];
                Double avgAge = (Double) row[3];
                System.out.printf("%s\t%s\t\t%d\t\t%.2f%n", 
                    courseId, courseName, enrollmentCount, avgAge);
            }
        }
    }
}

