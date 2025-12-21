import com.krowfeather.Main;
import com.krowfeather.dao.CourseDao;
import com.krowfeather.entity.Course;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = Main.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CourseDaoTest {

    @Autowired
    private CourseDao courseDao;

    private Course testCourse;

    @BeforeEach
    public void setUp() {
        testCourse = new Course();
        testCourse.setId("C001");
        testCourse.setName("Java程序设计");
        testCourse.setCredit(3.0f);
    }

    @Test
    @Order(1)
    public void testSaveCourse() {
        System.out.println("=== 测试保存课程 ===");
        Course savedCourse = courseDao.save(testCourse);
        System.out.println("保存课程成功: " + savedCourse);
    }

    @Test
    @Order(2)
    public void testFindCourseById() {
        System.out.println("=== 测试根据ID查找课程 ===");
        Optional<Course> foundCourse = courseDao.findById("C001");
        if (foundCourse.isPresent()) {
            System.out.println("查找课程结果: " + foundCourse.get());
        } else {
            System.out.println("未找到课程");
        }
    }

    @Test
    @Order(3)
    public void testFindAllCourses() {
        System.out.println("=== 测试查找所有课程 ===");
        List<Course> courses = courseDao.findAll();
        System.out.println("所有课程数量: " + courses.size());
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    @Test
    @Order(4)
    public void testFindCoursesByName() {
        System.out.println("=== 测试根据课程名称查找课程 ===");
        List<Course> courses = courseDao.findByName("Java程序设计");
        System.out.println("根据课程名称查找结果: " + courses.size() + " 条记录");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    @Test
    @Order(5)
    public void testFindCoursesByCreditBetween() {
        System.out.println("=== 测试根据学分范围查找课程 ===");
        List<Course> courses = courseDao.findByCreditBetween(2.0f, 4.0f);
        System.out.println("根据学分范围查找结果: " + courses.size() + " 条记录");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    @Test
    @Order(6)
    public void testFindCoursesByNameContaining() {
        System.out.println("=== 测试根据课程名称模糊查询 ===");
        List<Course> courses = courseDao.findCoursesByNameContaining("Java");
        System.out.println("根据课程名称模糊查询结果: " + courses.size() + " 条记录");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    @Test
    @Order(7)
    public void testUpdateCourse() {
        System.out.println("=== 测试更新课程信息 ===");
        Optional<Course> courseOpt = courseDao.findById("C001");
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            course.setName("高级Java程序设计");
            course.setCredit(4.5f);
            Course updatedCourse = courseDao.save(course);
            System.out.println("更新课程信息成功: " + updatedCourse);
        } else {
            System.out.println("未找到要更新的课程");
        }
    }

    @Test
    @Order(8)
    public void testUpdateCourseCredit() {
        System.out.println("=== 测试更新课程学分 ===");
        int result = courseDao.updateCourseCredit("C001", 4.0f);
        System.out.println("更新课程学分成功，影响行数: " + result);

        Optional<Course> updatedCourse = courseDao.findById("C001");
        if (updatedCourse.isPresent()) {
            System.out.println("更新后的课程信息: " + updatedCourse.get());
        }
    }

    @Test
    @Order(9)
    public void testDeleteCourse() {
        System.out.println("=== 测试删除课程 ===");
        courseDao.deleteById("C001");
        Optional<Course> deletedCourse = courseDao.findById("C001");
        if (deletedCourse.isPresent()) {
            System.out.println("课程仍存在: " + deletedCourse.get());
        } else {
            System.out.println("删除课程成功");
        }
    }
}
