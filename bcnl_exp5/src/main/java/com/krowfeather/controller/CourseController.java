package com.krowfeather.controller;

import com.krowfeather.entity.Course;
import com.krowfeather.entity.Result;
import com.krowfeather.service.CourseService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Resource
    private CourseService courseService;

    // 新增课程
    @PostMapping
    public Result addCourse(@RequestBody Course course) {
        try {
            courseService.save(course);
            return Result.success("课程添加成功");
        } catch (Exception e) {
            return Result.error("500", "课程添加失败: " + e.getMessage());
        }
    }

    // 根据ID查询课程
    @GetMapping("/{id}")
    public Result getCourseById(@PathVariable String id) {
        try {
            Course course = courseService.findById(id);
            if (course != null) {
                return Result.success(course);
            } else {
                return Result.error("404", "课程不存在");
            }
        } catch (Exception e) {
            return Result.error("500", "查询课程失败: " + e.getMessage());
        }
    }

    // 查询所有课程
    @GetMapping
    public Result getAllCourses() {
        try {
            List<Course> courses = courseService.findAll();
            return Result.success(courses);
        } catch (Exception e) {
            return Result.error("500", "查询所有课程失败: " + e.getMessage());
        }
    }

    // 更新课程
    @PutMapping("/{id}")
    public Result updateCourse(@PathVariable("id") String id, @RequestBody Course course) {
        System.out.println(id);
        System.out.println(course);
        try {
            course.setId(id);
            courseService.update(course);
            return Result.success("课程更新成功");
        } catch (Exception e) {
            return Result.error("500", "课程更新失败: " + e.getMessage());
        }
    }

    // 删除课程
    @DeleteMapping("/{id}")
    public Result deleteCourse(@PathVariable("id") String id) {
        try {
            Course course = new Course();
            course.setId(id);
            courseService.delete(course);
            return Result.success("课程删除成功");
        } catch (Exception e) {
            return Result.error("500", "课程删除失败: " + e.getMessage());
        }
    }
}
