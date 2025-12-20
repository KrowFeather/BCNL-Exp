import com.krowfeather.Main;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = Main.class)
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
        testUser.setPassword("123456");
        testUser.setRole(1);
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
    @Transactional
    @Rollback(false) // 不回滚，让数据可以供后续测试使用
    public void testSaveRegistration() {
        System.out.println("=== 测试保存选课记录 ===");
        Registration savedRegistration = registrationDao.save(testRegistration);
        // 在事务中访问懒加载属性
        System.out.println("保存选课记录成功: ID=" + savedRegistration.getId() + 
                         ", 用户=" + savedRegistration.getUser().getName() + 
                         ", 课程=" + savedRegistration.getCourse().getName());
    }

    @Test
    @Order(2)
    @Transactional
    public void testFindRegistrationById() {
        System.out.println("=== 测试根据ID查找选课记录 ===");
        List<Registration> registrations = registrationDao.findAll();
        if (!registrations.isEmpty()) {
            Long registrationId = registrations.get(0).getId();
            Optional<Registration> foundRegistration = registrationDao.findById(registrationId);
            if (foundRegistration.isPresent()) {
                Registration reg = foundRegistration.get();
                // 在事务中访问懒加载属性
                System.out.println("查找选课记录结果: ID=" + reg.getId() + 
                                 ", 用户=" + reg.getUser().getName() + 
                                 ", 课程=" + reg.getCourse().getName());
            } else {
                System.out.println("未找到选课记录");
            }
        } else {
            System.out.println("没有选课记录");
        }
    }

    @Test
    @Order(3)
    @Transactional
    public void testFindAllRegistrations() {
        System.out.println("=== 测试查找所有选课记录 ===");
        List<Registration> registrations = registrationDao.findAll();
        System.out.println("所有选课记录数量: " + registrations.size());
        for (Registration registration : registrations) {
            // 在事务中访问懒加载属性
            System.out.println("ID=" + registration.getId() + 
                             ", 用户=" + registration.getUser().getName() + 
                             ", 课程=" + registration.getCourse().getName());
        }
    }

    @Test
    @Order(4)
    @Transactional
    public void testFindByUserId() {
        System.out.println("=== 测试根据用户ID查找选课记录 ===");
        // 确保有选课记录存在
        List<Registration> existing = registrationDao.findByUserId("S20221145141");
        if (existing.isEmpty()) {
            // 如果没有记录，先创建一条
            Registration newReg = new Registration();
            newReg.setUser(testUser);
            newReg.setCourse(testCourse);
            registrationDao.save(newReg);
            System.out.println("创建测试选课记录");
        }
        
        List<Registration> registrations = registrationDao.findByUserId("S20221145141");
        System.out.println("根据用户ID查找结果: " + registrations.size() + " 条记录");
        if (registrations.isEmpty()) {
            System.out.println("未找到该用户的选课记录");
        } else {
            for (Registration registration : registrations) {
                // 在事务中访问懒加载属性
                System.out.println("ID=" + registration.getId() + 
                                 ", 用户=" + registration.getUser().getName() + 
                                 ", 课程=" + registration.getCourse().getName());
            }
        }
    }

    @Test
    @Order(5)
    @Transactional
    public void testFindByCourseId() {
        System.out.println("=== 测试根据课程ID查找选课记录 ===");
        // 确保有选课记录存在
        List<Registration> existing = registrationDao.findByCourseId("C002");
        if (existing.isEmpty()) {
            // 如果没有记录，先创建一条
            Registration newReg = new Registration();
            newReg.setUser(testUser);
            newReg.setCourse(testCourse);
            registrationDao.save(newReg);
            System.out.println("创建测试选课记录");
        }
        
        List<Registration> registrations = registrationDao.findByCourseId("C002");
        System.out.println("根据课程ID查找结果: " + registrations.size() + " 条记录");
        if (registrations.isEmpty()) {
            System.out.println("未找到该课程的选课记录");
        } else {
            for (Registration registration : registrations) {
                // 在事务中访问懒加载属性
                System.out.println("ID=" + registration.getId() + 
                                 ", 用户=" + registration.getUser().getName() + 
                                 ", 课程=" + registration.getCourse().getName());
            }
        }
    }

    @Test
    @Order(6)
    @Transactional
    public void testFindByUserIdAndCourseId() {
        System.out.println("=== 测试根据用户ID和课程ID联合查询 ===");
        // 确保有选课记录存在
        List<Registration> existing = registrationDao.findByUserIdAndCourseId("S20221145141", "C002");
        if (existing.isEmpty()) {
            // 如果没有记录，先创建一条
            Registration newReg = new Registration();
            newReg.setUser(testUser);
            newReg.setCourse(testCourse);
            registrationDao.save(newReg);
            System.out.println("创建测试选课记录");
        }
        
        List<Registration> registrations = registrationDao.findByUserIdAndCourseId("S20221145141", "C002");
        System.out.println("根据用户ID和课程ID查找结果: " + registrations.size() + " 条记录");
        if (registrations.isEmpty()) {
            System.out.println("未找到匹配的选课记录");
        } else {
            for (Registration registration : registrations) {
                // 在事务中访问懒加载属性
                System.out.println("ID=" + registration.getId() + 
                                 ", 用户=" + registration.getUser().getName() + 
                                 ", 课程=" + registration.getCourse().getName());
            }
        }
    }

    @Test
    @Order(7)
    @Transactional
    public void testCountByCourseId() {
        System.out.println("=== 测试统计课程选课人数 ===");
        // 确保有选课记录存在
        List<Registration> existing = registrationDao.findByCourseId("C002");
        if (existing.isEmpty()) {
            // 如果没有记录，先创建一条
            Registration newReg = new Registration();
            newReg.setUser(testUser);
            newReg.setCourse(testCourse);
            registrationDao.save(newReg);
            System.out.println("创建测试选课记录");
        }
        
        int count = registrationDao.countByCourseId("C002");
        System.out.println("课程选课人数: " + count);
    }

    @Test
    @Order(8)
    @Transactional
    public void testUpdateRegistration() {
        System.out.println("=== 测试更新选课记录 ===");
        List<Registration> registrations = registrationDao.findAll();
        if (!registrations.isEmpty()) {
            Registration registration = registrations.get(0);

            // 创建新的课程用于更新
            Course newCourse = new Course();
            newCourse.setId("C003");
            newCourse.setName("操作系统");
            newCourse.setCredit(3.5f);
            courseDao.save(newCourse);
            System.out.println("创建新课程: " + newCourse);

            registration.setCourse(newCourse);
            Registration updatedRegistration = registrationDao.save(registration);
            // 在事务中访问懒加载属性
            System.out.println("更新选课记录成功: ID=" + updatedRegistration.getId() + 
                             ", 用户=" + updatedRegistration.getUser().getName() + 
                             ", 课程=" + updatedRegistration.getCourse().getName());

            // 清理测试数据
            courseDao.deleteById("C003");
        } else {
            System.out.println("没有选课记录可以更新");
        }
    }

    @Test
    @Order(9)
    public void testDeleteByUserId() {
        System.out.println("=== 测试根据用户ID删除选课记录 ===");
        // 先确保有选课记录存在（重新创建一条，因为前面的测试可能已经删除）
        Registration newReg = new Registration();
        newReg.setUser(testUser);
        newReg.setCourse(testCourse);
        registrationDao.save(newReg);
        System.out.println("创建测试选课记录");
        
        // 检查删除前的记录数量
        List<Registration> beforeDelete = registrationDao.findByUserId("S20221145141");
        int beforeCount = beforeDelete.size();
        System.out.println("删除前选课记录数量: " + beforeCount);

        if (beforeCount > 0) {
            int result = registrationDao.deleteByUserId("S20221145141");
            System.out.println("根据用户ID删除选课记录成功，影响行数: " + result);

            List<Registration> afterDelete = registrationDao.findByUserId("S20221145141");
            System.out.println("删除后选课记录数量: " + afterDelete.size());
        } else {
            System.out.println("没有选课记录可以删除");
        }
    }

    @Test
    @Order(10)
    @Transactional
    public void testDeleteRegistration() {
        System.out.println("=== 测试删除选课记录 ===");
        // 重新创建一条选课记录用于删除测试
        Registration newRegistration = new Registration();
        newRegistration.setUser(testUser);
        newRegistration.setCourse(testCourse);
        Registration savedRegistration = registrationDao.save(newRegistration);
        Long registrationId = savedRegistration.getId();
        // 在事务中访问懒加载属性
        System.out.println("创建选课记录: ID=" + savedRegistration.getId() + 
                         ", 用户=" + savedRegistration.getUser().getName() + 
                         ", 课程=" + savedRegistration.getCourse().getName());

        registrationDao.deleteById(registrationId);
        Optional<Registration> deletedRegistration = registrationDao.findById(registrationId);
        if (deletedRegistration.isPresent()) {
            System.out.println("选课记录仍存在");
        } else {
            System.out.println("删除选课记录成功");
        }
    }

    @AfterEach
    public void tearDown() {
        // 注意：由于测试之间需要共享数据，所以不在每个测试后清理
        // 只在最后清理或手动清理测试数据
        // 如果需要清理，可以取消下面的注释
        /*
        try {
            registrationDao.deleteByUserId("S20221145141");
        } catch (Exception e) {
            // 忽略异常
        }
        try {
            userDao.deleteById("S20221145141");
        } catch (Exception e) {
            // 忽略异常
        }
        try {
            courseDao.deleteById("C002");
        } catch (Exception e) {
            // 忽略异常
        }
        */
    }
}
