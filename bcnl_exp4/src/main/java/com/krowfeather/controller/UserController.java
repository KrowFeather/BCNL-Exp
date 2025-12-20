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

@Tag(name = "用户管理", description = "用户相关的API接口")
@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    private UserService userService;

    @Operation(summary = "新增用户", description = "添加一个新的用户信息")
    @PostMapping
    public Result addUser(
            @Parameter(description = "用户信息")
            @RequestBody User user) {
        try {
            userService.save(user);
            return Result.success("用户添加成功");
        } catch (Exception e) {
            return Result.error("500", "用户添加失败: " + e.getMessage());
        }
    }

    @Operation(summary = "根据ID查询用户", description = "根据用户ID查询用户详细信息")
    @Parameters({
            @Parameter(name = "id", description = "用户ID", required = true)
    })
    @GetMapping("/{id}")
    public Result getStudentById(
            @PathVariable String id) {
        User user = userService.findById(id);
        if (user != null) {
            return Result.success(user);
        } else {
            return Result.error("404", "用户不存在");
        }
    }

    @Operation(summary = "查询所有用户", description = "获取所有用户的列表信息")
    @GetMapping
    public Result getAllUsers() {
        List<User> users = userService.findAll();
        return Result.success(users);
    }

    @Operation(summary = "更新用户信息", description = "根据用户ID更新用户信息")
    @Parameters({
            @Parameter(name = "id", description = "用户ID", required = true)
    })
    @PutMapping("/{id}")
    public Result updateUser(
            @PathVariable String id,
            @Parameter(description = "更新后的用户信息")
            @RequestBody User user) {
        try {
            user.setId(id);
            userService.update(user);
            return Result.success("用户更新成功");
        } catch (Exception e) {
            return Result.error("500", "用户更新失败: " + e.getMessage());
        }
    }

    @Operation(summary = "删除用户", description = "根据用户ID删除用户信息")
    @Parameters({
            @Parameter(name = "id", description = "用户ID", required = true)
    })
    @DeleteMapping("/{id}")
    public Result deleteUser(
            @PathVariable String id) {
        try {
            User user = new User();
            user.setId(id);
            userService.delete(user);
            return Result.success("用户删除成功");
        } catch (Exception e) {
            return Result.error("500", "用户删除失败: " + e.getMessage());
        }
    }

    @Operation(summary = "用户注册", description = "用户注册新账号")
    @PostMapping("/register")
    public Result register(
            @Parameter(description = "注册用户信息")
            @RequestBody User user) {
        boolean result = userService.register(user);
        return result ? Result.success("注册成功") : Result.error("500", "注册失败");
    }

    @Operation(summary = "用户登录", description = "用户登录验证")
    @PostMapping("/login")
    public Result login(
            @Parameter(description = "登录用户信息")
            @RequestBody User user) {
        boolean result = userService.login(user);
        return result ? Result.success("登录成功") : Result.error("500", "登录失败");
    }
}
