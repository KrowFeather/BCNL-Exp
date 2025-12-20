package com.krowfeather.controller;

import com.krowfeather.entity.Result;
import com.krowfeather.entity.User;
import com.krowfeather.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    private UserService userService;

    // 新增用户
    @PostMapping
    public Result addUser(@RequestBody User user) {
        try {
            userService.save(user);
            return Result.success("用户添加成功");
        } catch (Exception e) {
            return Result.error("500", "用户添加失败: " + e.getMessage());
        }
    }

    // 根据ID查询学生
    @GetMapping("/{id}")
    public Result getStudentById(@PathVariable String id) {
        User user = userService.findById(id);
        if (user != null) {
            return Result.success(user);
        } else {
            return Result.error("404", "用户不存在");
        }
    }

    // 查询所有学生
    @GetMapping
    public Result getAllUsers() {
        List<User> users = userService.findAll();
        return Result.success(users);
    }

    // 更新学生
    @PutMapping("/{id}")
    public Result updateUser(@PathVariable String id, @RequestBody User user) {
        try {
            user.setId(id);
            userService.update(user);
            return Result.success("用户更新成功");
        } catch (Exception e) {
            return Result.error("500", "用户更新失败: " + e.getMessage());
        }
    }

    // 删除学生
    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable String id) {
        try {
            User user = new User();
            user.setId(id);
            userService.delete(user);
            return Result.success("用户删除成功");
        } catch (Exception e) {
            return Result.error("500", "用户删除失败: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        boolean result = userService.register(user);
        return result ? Result.success("注册成功") : Result.error("500", "注册失败");
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        boolean result = userService.login(user);
        return result ? Result.success("登录成功") : Result.error("500", "登录失败");
    }
}
