
import com.krowfeather.dao.CourseDao;
import com.krowfeather.entity.Course;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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
        assertNotNull(savedCourse);
        assertEquals(testCourse.getId(), savedCourse.getId());
        System.out.println("保存课程成功: " + savedCourse);
    }

    @Test
    @Order(2)
    public void testFindCourseById() {
        System.out.println("=== 测试根据ID查找课程 ===");
        Optional<Course> foundCourse = courseDao.findById("C001");
        assertTrue(foundCourse.isPresent());
        assertEquals("Java程序设计", foundCourse.get().getName());
        System.out.println("查找课程结果: " + foundCourse.get());
    }

    @Test
    @Order(3)
    public void testFindAllCourses() {
        System.out.println("=== 测试查找所有课程 ===");
        List<Course> courses = courseDao.findAll();
        assertFalse(courses.isEmpty());
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
        assertFalse(courses.isEmpty());
        assertEquals("Java程序设计", courses.getFirst().getName());
        System.out.println("根据课程名称查找结果: " + courses.size() + " 条记录");
    }

    @Test
    @Order(5)
    public void testUpdateCourseCredit() {
        System.out.println("=== 测试更新课程学分 ===");
        int result = courseDao.updateCourseCredit("C001", 4.0f);
        assertTrue(result > 0);
        System.out.println("更新课程学分成功，影响行数: " + result);

        Optional<Course> updatedCourse = courseDao.findById("C001");
        assertEquals(4.0f, updatedCourse.get().getCredit());
    }

    @Test
    @Order(6)
    public void testCalculateAverageCredit() {
        System.out.println("=== 测试计算平均学分 ===");
        Float avgCredit = courseDao.calculateAverageCredit();
        assertNotNull(avgCredit);
        System.out.println("平均学分: " + avgCredit);
    }

    @Test
    @Order(7)
    public void testDeleteCourse() {
        System.out.println("=== 测试删除课程 ===");
        courseDao.deleteById("C001");
        Optional<Course> deletedCourse = courseDao.findById("C001");
        assertFalse(deletedCourse.isPresent());
        System.out.println("删除课程成功");
    }
}
