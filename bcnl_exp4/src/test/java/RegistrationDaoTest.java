import com.krowfeather.dao.RegistrationDao;
import com.krowfeather.dao.CourseDao;
import com.krowfeather.dao.UserDao;
import com.krowfeather.entity.Registration;
import com.krowfeather.entity.Course;
import com.krowfeather.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegistrationDaoTest {

    @Autowired
    private RegistrationDao registrationDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CourseDao courseDao;

    private Registration testRegistration;
    private User testUser;
    private Course testCourse;

    @BeforeEach
    public void setUp() {
        // 准备测试数据
        testUser = new User();
        testUser.setId("S20221145141");
        testUser.setName("王五");
        testUser.setGender(1);
        testUser.setBirthday(new Date());
        userDao.save(testUser);

        testCourse = new Course();
        testCourse.setId("C002");
        testCourse.setName("数据库原理");
        testCourse.setCredit(2.5f);
        courseDao.save(testCourse);

        testRegistration = new Registration();
        testRegistration.setUser(testUser);
        testRegistration.setCourse(testCourse);
    }

    @Test
    @Order(1)
    public void testSaveRegistration() {
        System.out.println("=== 测试保存选课记录 ===");
        Registration savedRegistration = registrationDao.save(testRegistration);
        assertNotNull(savedRegistration);
        assertNotNull(savedRegistration.getId());
        System.out.println("保存选课记录成功: " + savedRegistration);
    }

    @Test
    @Order(2)
    public void testFindRegistrationById() {
        System.out.println("=== 测试根据ID查找选课记录 ===");
        List<Registration> registrations = registrationDao.findAll();
        assertFalse(registrations.isEmpty());
        Long registrationId = registrations.get(0).getId();

        Optional<Registration> foundRegistration = registrationDao.findById(registrationId);
        assertTrue(foundRegistration.isPresent());
        assertEquals("王五", foundRegistration.get().getUser().getName());
        System.out.println("查找选课记录结果: " + foundRegistration.get());
    }

    @Test
    @Order(3)
    public void testFindAllRegistrations() {
        System.out.println("=== 测试查找所有选课记录 ===");
        List<Registration> registrations = registrationDao.findAll();
        assertFalse(registrations.isEmpty());
        System.out.println("所有选课记录数量: " + registrations.size());
        for (Registration registration : registrations) {
            System.out.println(registration);
        }
    }

    @Test
    @Order(4)
    public void testFindByStudentId() {
        System.out.println("=== 测试根据学生ID查找选课记录 ===");
        List<Registration> registrations = registrationDao.findByUserId("S20221145141");
        assertFalse(registrations.isEmpty());
        assertEquals("王五", registrations.get(0).getUser().getName());
        System.out.println("根据用户ID查找结果: " + registrations.size() + " 条记录");
    }

    @Test
    @Order(5)
    public void testCountByCourseId() {
        System.out.println("=== 测试统计课程选课人数 ===");
        int count = registrationDao.countByCourseId("C002");
        assertTrue(count > 0);
        System.out.println("课程选课人数: " + count);
    }

    @Test
    @Order(6)
    public void testDeleteRegistration() {
        System.out.println("=== 测试删除选课记录 ===");
        List<Registration> registrations = registrationDao.findAll();
        assertFalse(registrations.isEmpty());
        Long registrationId = registrations.get(0).getId();

        registrationDao.deleteById(registrationId);
        Optional<Registration> deletedRegistration = registrationDao.findById(registrationId);
        assertFalse(deletedRegistration.isPresent());
        System.out.println("删除选课记录成功");
    }

    @AfterEach
    public void tearDown() {
        // 清理测试数据
        userDao.deleteById("S20221145141");
        courseDao.deleteById("C002");
    }
}
