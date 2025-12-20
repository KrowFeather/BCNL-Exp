import com.krowfeather.dao.CourseDao;
import com.krowfeather.dao.StudentDao;
import com.krowfeather.entity.Course;
import com.krowfeather.entity.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DaoTest {

    private StudentDao studentDao;
    private CourseDao courseDao;
    private Student testStudent;
    private Course testCourse;

    @BeforeEach
    public void setUp() {
        // 初始化DAO对象
        studentDao = new StudentDao();
        courseDao = new CourseDao();

        // 创建测试用的学生对象
        testStudent = new Student();
        testStudent.setId("20221145140");
        testStudent.setName("张三");
        testStudent.setGender(1);
        testStudent.setAge(20);
        testStudent.setBirthday(Date.valueOf(LocalDate.of(2003, 1, 21)));
        // 设置密码为"123456"
        testStudent.setPassword("123456");

        // 创建测试用的课程对象
        testCourse = new Course();
        testCourse.setId("1");
        testCourse.setCourseName("Java程序设计");
        testCourse.setCredit(3.0f);
    }

    @AfterEach
    public void tearDown() {
        // 清理测试数据
        studentDao = null;
        courseDao = null;
        testStudent = null;
        testCourse = null;
    }

    @Test
    @Order(1)
    public void testStudentSaveAndFindById() {
        System.out.println("=== 测试保存和查找学生 ===");
        // 测试保存学生
        studentDao.save(testStudent);
        System.out.println("保存学生: " + testStudent);
        // 测试根据ID查找学生
        Optional<Student> foundStudent = studentDao.findById(testStudent.getId());
        System.out.println("根据ID查找学生结果: " + foundStudent.orElse(null));
    }

    @Order(2)
    @Test
    public void testStudentUpdate() {
        System.out.println("=== 测试更新学生 ===");
        testStudent.setName("李四");
        testStudent.setAge(21);
        studentDao.update(testStudent);
        System.out.println("更新学生信息完成");
        Optional<Student> updatedStudent = studentDao.findById(testStudent.getId());
        System.out.println("更新后查找学生结果: " + updatedStudent.orElse(null));
    }

    @Order(3)
    @Test
    public void testStudentDelete() {
        System.out.println("=== 测试删除学生 ===");
        // 删除学生
        studentDao.delete(testStudent);
        System.out.println("删除学生完成");
        // 验证删除结果
        Optional<Student> deletedStudent = studentDao.findById(testStudent.getId());
        System.out.println("删除后查找学生结果: " + deletedStudent.orElse(null));
    }
    @Order(4)
    @Test
    public void testCourseSaveAndFindById() {
        System.out.println("=== 测试保存和查找课程 ===");
        // 测试保存课程
        courseDao.save(testCourse);
        System.out.println("保存课程: " + testCourse);
        // 测试根据ID查找课程
        Optional<Course> foundCourse = courseDao.findById(testCourse.getId());
        System.out.println("根据ID查找课程结果: " + foundCourse.orElse(null));
    }

    @Order(5)
    @Test
    public void testCourseUpdate() {
        System.out.println("=== 测试更新课程 ===");
        // 修改课程信息
        testCourse.setCourseName("高级Java程序设计");
        testCourse.setCredit(4.0f);
        courseDao.update(testCourse);
        System.out.println("更新课程信息完成");
        // 验证更新结果
        Optional<Course> updatedCourse = courseDao.findById(testCourse.getId());
        System.out.println("更新后查找课程结果: " + updatedCourse.orElse(null));
    }

    @Order(6)
    @Test
    public void testCourseDelete() {
        System.out.println("=== 测试删除课程 ===");
        // 删除课程
        courseDao.delete(testCourse);
        System.out.println("删除课程完成");
        // 验证删除结果
        Optional<Course> deletedCourse = courseDao.findById(testCourse.getId());
        System.out.println("删除后查找课程结果: " + deletedCourse.orElse(null));
    }
}
