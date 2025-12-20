package com.krowfeather.controller;

import com.krowfeather.entity.Course;
import com.krowfeather.entity.Result;
import com.krowfeather.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "课程管理", description = "课程相关的API接口")
@RestController
@RequestMapping("/courses")
public class CourseController {

    @Resource
    private CourseService courseService;

    @Operation(summary = "新增课程", description = "添加一个新的课程信息")
    @PostMapping
    public Result addCourse(
            @Parameter(description = "课程信息")
            @RequestBody Course course) {
        try {
            courseService.save(course);
            return Result.success("课程添加成功");
        } catch (Exception e) {
            return Result.error("500", "课程添加失败: " + e.getMessage());
        }
    }

    @Operation(summary = "根据ID查询课程", description = "根据课程ID查询课程详细信息")
    @Parameters({
            @Parameter(name = "id", description = "课程ID", required = true)
    })
    @GetMapping("/{id}")
    public Result getCourseById(
            @PathVariable String id) {
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

    @Operation(summary = "查询所有课程", description = "获取所有课程的列表信息")
    @GetMapping
    public Result getAllCourses() {
        try {
            List<Course> courses = courseService.findAll();
            return Result.success(courses);
        } catch (Exception e) {
            return Result.error("500", "查询所有课程失败: " + e.getMessage());
        }
    }

    @Operation(summary = "更新课程信息", description = "根据课程ID更新课程信息")
    @Parameters({
            @Parameter(name = "id", description = "课程ID", required = true)
    })
    @PutMapping("/{id}")
    public Result updateCourse(
            @PathVariable("id") String id,
            @Parameter(description = "更新后的课程信息")
            @RequestBody Course course) {
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

    @Operation(summary = "删除课程", description = "根据课程ID删除课程信息")
    @Parameters({
            @Parameter(name = "id", description = "课程ID", required = true)
    })
    @DeleteMapping("/{id}")
    public Result deleteCourse(
            @PathVariable("id") String id) {
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
