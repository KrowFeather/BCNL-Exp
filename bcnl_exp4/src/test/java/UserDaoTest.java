import com.krowfeather.Main;
import com.krowfeather.dao.UserDao;
import com.krowfeather.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = Main.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User();
        testUser.setId("U001");
        testUser.setName("张三");
        testUser.setGender(1);
        testUser.setBirthday(new Date());
        testUser.setPassword("123456");
        testUser.setRole(1);
    }

    @Test
    @Order(1)
    public void testSaveUser() {
        System.out.println("=== 测试保存用户 ===");
        User savedUser = userDao.save(testUser);
        System.out.println("保存用户成功: " + savedUser);
    }

    @Test
    @Order(2)
    public void testFindUserById() {
        System.out.println("=== 测试根据ID查找用户 ===");
        Optional<User> foundUser = userDao.findById("U001");
        if (foundUser.isPresent()) {
            System.out.println("查找用户结果: " + foundUser.get());
        } else {
            System.out.println("未找到用户");
        }
    }

    @Test
    @Order(3)
    public void testFindAllUsers() {
        System.out.println("=== 测试查找所有用户 ===");
        List<User> users = userDao.findAll();
        System.out.println("所有用户数量: " + users.size());
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    @Order(4)
    public void testFindUsersByName() {
        System.out.println("=== 测试根据姓名查找用户 ===");
        List<User> users = userDao.findByName("张三");
        System.out.println("根据姓名查找结果: " + users.size() + " 条记录");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    @Order(5)
    public void testFindUsersByGenderAndRole() {
        System.out.println("=== 测试根据性别和角色查找用户 ===");
        List<User> users = userDao.findByGenderAndRole(1, 1);
        System.out.println("根据性别和角色查找结果: " + users.size() + " 条记录");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    @Order(6)
    public void testFindUsersByNameContaining() {
        System.out.println("=== 测试根据姓名模糊查询用户 ===");
        List<User> users = userDao.findUsersByNameContaining("三");
        System.out.println("根据姓名模糊查询结果: " + users.size() + " 条记录");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    @Order(7)
    public void testUpdateUser() {
        System.out.println("=== 测试更新用户信息 ===");
        Optional<User> userOpt = userDao.findById("U001");
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setName("张三三");
            user.setGender(0);
            User updatedUser = userDao.save(user);
            System.out.println("更新用户信息成功: " + updatedUser);
        } else {
            System.out.println("未找到要更新的用户");
        }
    }

    @Test
    @Order(8)
    public void testUpdateUserRole() {
        System.out.println("=== 测试更新用户角色 ===");
        int result = userDao.updateUserRole("U001", 2);
        System.out.println("更新用户角色成功，影响行数: " + result);

        Optional<User> updatedUser = userDao.findById("U001");
        if (updatedUser.isPresent()) {
            System.out.println("更新后的用户信息: " + updatedUser.get());
        }
    }

    @Test
    @Order(9)
    public void testDeleteUser() {
        System.out.println("=== 测试删除用户 ===");
        userDao.deleteById("U001");
        Optional<User> deletedUser = userDao.findById("U001");
        if (deletedUser.isPresent()) {
            System.out.println("用户仍存在: " + deletedUser.get());
        } else {
            System.out.println("删除用户成功");
        }
    }
}
