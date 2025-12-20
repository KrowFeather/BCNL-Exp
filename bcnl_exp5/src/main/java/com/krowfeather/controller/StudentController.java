package com.krowfeather.controller;

import com.krowfeather.entity.Result;
import com.krowfeather.entity.Student;
import com.krowfeather.service.StudentService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Resource
    private StudentService studentService;

    // 新增学生
    @PostMapping
    public Result addStudent(@RequestBody Student student) {
        try {
            studentService.save(student);
            return Result.success("学生添加成功");
        } catch (Exception e) {
            return Result.error("500", "学生添加失败: " + e.getMessage());
        }
    }

    // 根据ID查询学生
    @GetMapping("/{id}")
    public Result getStudentById(@PathVariable String id) {
        Student student = studentService.findById(id);
        if (student != null) {
            return Result.success(student);
        } else {
            return Result.error("404", "学生不存在");
        }
    }

    // 查询所有学生
    @GetMapping
    public Result getAllStudents() {
        List<Student> students = studentService.findAll();
        return Result.success(students);
    }

    // 更新学生
    @PutMapping("/{id}")
    public Result updateStudent(@PathVariable String id, @RequestBody Student student) {
        try {
            student.setId(id);
            studentService.update(student);
            return Result.success("学生更新成功");
        } catch (Exception e) {
            return Result.error("500", "学生更新失败: " + e.getMessage());
        }
    }

    // 删除学生
    @DeleteMapping("/{id}")
    public Result deleteStudent(@PathVariable String id) {
        try {
            Student student = new Student();
            student.setId(id);
            studentService.delete(student);
            return Result.success("学生删除成功");
        } catch (Exception e) {
            return Result.error("500", "学生删除失败: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public Result register(@RequestBody Student student) {
        boolean result = studentService.register(student);
        return result ? Result.success("注册成功") : Result.error("500", "注册失败");
    }

    @PostMapping("/login")
    public Result login(@RequestBody Student student) {
        boolean result = studentService.login(student);
        return result ? Result.success("登录成功") : Result.error("500", "登录失败");
    }
}
