package com.krowfeather.controller;

import com.krowfeather.entity.Result;
import com.krowfeather.entity.User;
import com.krowfeather.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理控制器
 * 提供用户相关的RESTful API接口
 */
@Tag(name = "用户管理", description = "用户相关的API接口")
@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 新增用户
     */
    @Operation(summary = "新增用户", description = "添加一个新的用户信息")
    @PostMapping
    public Result addUser(@Parameter(description = "用户信息") @RequestBody User user) {
        try {
            userService.save(user);
            return Result.success("用户添加成功");
        } catch (Exception e) {
            return Result.error("500", "用户添加失败: " + e.getMessage());
        }
    }

    /**
     * 根据ID查询用户
     */
    @Operation(summary = "根据ID查询用户", description = "根据用户ID查询用户详细信息")
    @Parameters({
            @Parameter(name = "id", description = "用户ID", required = true)
    })
    @GetMapping("/{id}")
    public Result getStudentById(@PathVariable("id") String id) {
        User user = userService.findById(id);
        if (user != null) {
            return Result.success(user);
        } else {
            return Result.error("404", "用户不存在");
        }
    }

    /**
     * 查询所有用户
     */
    @Operation(summary = "查询所有用户", description = "获取所有用户的列表信息")
    @GetMapping
    public Result getAllUsers() {
        List<User> users = userService.findAll();
        return Result.success(users);
    }

    /**
     * 更新用户信息
     */
    @Operation(summary = "更新用户信息", description = "根据用户ID更新用户信息")
    @Parameters({
            @Parameter(name = "id", description = "用户ID", required = true)
    })
    @PutMapping("/{id}")
    public Result updateUser(
            @PathVariable("id") String id,
            @Parameter(description = "更新后的用户信息") @RequestBody User user) {
        try {
            user.setId(id);
            userService.update(user);
            return Result.success("用户更新成功");
        } catch (Exception e) {
            return Result.error("500", "用户更新失败: " + e.getMessage());
        }
    }

    /**
     * 删除用户
     */
    @Operation(summary = "删除用户", description = "根据用户ID删除用户信息")
    @Parameters({
            @Parameter(name = "id", description = "用户ID", required = true)
    })
    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable("id") String id) {
        try {
            User user = new User();
            user.setId(id);
            userService.delete(user);
            return Result.success("用户删除成功");
        } catch (Exception e) {
            return Result.error("500", "用户删除失败: " + e.getMessage());
        }
    }

    /**
     * 用户注册
     */
    @Operation(summary = "用户注册", description = "用户注册新账号")
    @PostMapping("/register")
    public Result register(@Parameter(description = "注册用户信息") @RequestBody User user) {
        boolean result = userService.register(user);
        return result ? Result.success("注册成功") : Result.error("500", "注册失败");
    }

    /**
     * 用户登录
     */
    @Operation(summary = "用户登录", description = "用户登录验证")
    @PostMapping("/login")
    public Result login(@Parameter(description = "登录用户信息") @RequestBody User user) {
        boolean result = userService.login(user);
        return result ? Result.success("登录成功") : Result.error("500", "登录失败");
    }

    /**
     * 根据姓名查找用户
     */
    @Operation(summary = "根据姓名查找用户", description = "根据用户姓名精确查询用户列表")
    @Parameters({
            @Parameter(name = "name", description = "用户姓名", required = true)
    })
    @GetMapping("/name/{name}")
    public Result findUsersByName(@PathVariable("name") String name) {
        try {
            List<User> users = userService.findByName(name);
            return Result.success(users);
        } catch (Exception e) {
            return Result.error("500", "查询失败: " + e.getMessage());
        }
    }

    /**
     * 根据性别和角色查找用户
     */
    @Operation(summary = "根据性别和角色查找用户", description = "根据性别和角色查询用户列表")
    @Parameters({
            @Parameter(name = "gender", description = "性别（0-女，1-男）", required = true),
            @Parameter(name = "role", description = "角色", required = true)
    })
    @GetMapping("/gender/{gender}/role/{role}")
    public Result findUsersByGenderAndRole(
            @PathVariable("gender") Integer gender,
            @PathVariable("role") Integer role) {
        try {
            List<User> users = userService.findByGenderAndRole(gender, role);
            return Result.success(users);
        } catch (Exception e) {
            return Result.error("500", "查询失败: " + e.getMessage());
        }
    }

    /**
     * 根据姓名模糊查询用户
     */
    @Operation(summary = "根据姓名模糊查询用户", description = "根据用户姓名进行模糊查询")
    @Parameters({
            @Parameter(name = "name", description = "用户姓名关键字", required = true)
    })
    @GetMapping("/search/{name}")
    public Result findUsersByNameContaining(@PathVariable("name") String name) {
        try {
            List<User> users = userService.findUsersByNameContaining(name);
            return Result.success(users);
        } catch (Exception e) {
            return Result.error("500", "查询失败: " + e.getMessage());
        }
    }

    /**
     * 更新用户角色
     */
    @Operation(summary = "更新用户角色", description = "根据用户ID更新用户角色")
    @Parameters({
            @Parameter(name = "id", description = "用户ID", required = true),
            @Parameter(name = "role", description = "新角色", required = true)
    })
    @PutMapping("/{id}/role/{role}")
    public Result updateUserRole(
            @PathVariable("id") String id,
            @PathVariable("role") Integer role) {
        try {
            int result = userService.updateUserRole(id, role);
            if (result > 0) {
                return Result.success("用户角色更新成功");
            } else {
                return Result.error("404", "用户不存在或更新失败");
            }
        } catch (Exception e) {
            return Result.error("500", "更新失败: " + e.getMessage());
        }
    }
}
