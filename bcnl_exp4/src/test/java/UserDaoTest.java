import com.krowfeather.dao.UserDao;
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
        assertNotNull(savedUser);
        assertEquals(testUser.getId(), savedUser.getId());
        System.out.println("保存用户成功: " + savedUser);
    }

    @Test
    @Order(2)
    public void testFindUserById() {
        System.out.println("=== 测试根据ID查找用户 ===");
        Optional<User> foundUser = userDao.findById("U001");
        assertTrue(foundUser.isPresent());
        assertEquals("张三", foundUser.get().getName());
        System.out.println("查找用户结果: " + foundUser.get());
    }

    @Test
    @Order(3)
    public void testFindAllUsers() {
        System.out.println("=== 测试查找所有用户 ===");
        List<User> users = userDao.findAll();
        assertFalse(users.isEmpty());
        System.out.println("所有用户数量: " + users.size());
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    @Order(4)
    public void testFindUsersByName() {
        System.out.println("=== 测试根据姓名查找用户 ===");
        List<User> users = ((UserDao) userDao).findByName("张三");
        assertFalse(users.isEmpty());
        assertEquals("张三", users.get(0).getName());
        System.out.println("根据姓名查找结果: " + users.size() + " 条记录");
    }

    @Test
    @Order(5)
    public void testUpdateUserRole() {
        System.out.println("=== 测试更新用户角色 ===");
        int result = ((UserDao) userDao).updateUserRole("U001", 2);
        assertTrue(result > 0);
        System.out.println("更新用户角色成功，影响行数: " + result);

        Optional<User> updatedUser = userDao.findById("U001");
        assertEquals(2, updatedUser.get().getRole());
    }

    @Test
    @Order(6)
    public void testDeleteUser() {
        System.out.println("=== 测试删除用户 ===");
        userDao.deleteById("U001");
        Optional<User> deletedUser = userDao.findById("U001");
        assertFalse(deletedUser.isPresent());
        System.out.println("删除用户成功");
    }
}
