package com.krowfeather.controller;

import com.krowfeather.entity.Registration;
import com.krowfeather.entity.Result;
import com.krowfeather.service.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 选课管理控制器
 * 提供选课相关的RESTful API接口
 */
@Tag(name = "选课管理", description = "选课相关的API接口")
@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    @Resource
    private RegistrationService registrationService;

    /**
     * 新增选课记录
     */
    @Operation(summary = "新增选课记录", description = "添加一个新的选课记录")
    @PostMapping
    public Result addRegistration(@Parameter(description = "选课信息") @RequestBody Registration registration) {
        try {
            registrationService.register(registration.getUser(), registration.getCourse());
            return Result.success("选课记录添加成功");
        } catch (Exception e) {
            return Result.error("500", "选课记录添加失败: " + e.getMessage());
        }
    }

    /**
     * 根据ID查询选课记录
     */
    @Operation(summary = "根据ID查询选课记录", description = "根据选课记录ID查询详细信息")
    @Parameters({
            @Parameter(name = "id", description = "选课记录ID", required = true)
    })
    @GetMapping("/{id}")
    public Result getRegistrationById(@PathVariable("id") Long id) {
        Registration registration = registrationService.findById(id);
        if (registration != null) {
            return Result.success(registration);
        } else {
            return Result.error("404", "选课记录不存在");
        }
    }

    /**
     * 查询所有选课记录
     */
    @Operation(summary = "查询所有选课记录", description = "获取所有选课记录的列表信息")
    @GetMapping
    public Result getAllRegistrations() {
        List<Registration> registrations = registrationService.findAll();
        return Result.success(registrations);
    }

    /**
     * 删除选课记录
     */
    @Operation(summary = "删除选课记录", description = "根据选课记录ID删除选课记录")
    @Parameters({
            @Parameter(name = "id", description = "选课记录ID", required = true)
    })
    @DeleteMapping("/{id}")
    public Result deleteRegistration(@PathVariable("id") Long id) {
        try {
            Registration registration = new Registration();
            registration.setId(id);
            registrationService.delete(registration);
            return Result.success("选课记录删除成功");
        } catch (Exception e) {
            return Result.error("500", "选课记录删除失败: " + e.getMessage());
        }
    }

    /**
     * 根据用户ID查找选课记录
     */
    @Operation(summary = "根据用户ID查找选课记录", description = "查询指定用户的所有选课记录")
    @Parameters({
            @Parameter(name = "userId", description = "用户ID", required = true)
    })
    @GetMapping("/user/{userId}")
    public Result getRegistrationsByUserId(@PathVariable("userId") String userId) {
        try {
            List<Registration> registrations = registrationService.findByUserId(userId);
            return Result.success(registrations);
        } catch (Exception e) {
            return Result.error("500", "查询失败: " + e.getMessage());
        }
    }

    /**
     * 根据课程ID查找选课记录
     */
    @Operation(summary = "根据课程ID查找选课记录", description = "查询指定课程的所有选课记录")
    @Parameters({
            @Parameter(name = "courseId", description = "课程ID", required = true)
    })
    @GetMapping("/course/{courseId}")
    public Result getRegistrationsByCourseId(@PathVariable("courseId") String courseId) {
        try {
            List<Registration> registrations = registrationService.findByCourseId(courseId);
            return Result.success(registrations);
        } catch (Exception e) {
            return Result.error("500", "查询失败: " + e.getMessage());
        }
    }

    /**
     * 根据用户ID删除选课记录
     */
    @Operation(summary = "根据用户ID删除选课记录", description = "删除指定用户的所有选课记录")
    @Parameters({
            @Parameter(name = "userId", description = "用户ID", required = true)
    })
    @DeleteMapping("/user/{userId}")
    public Result deleteRegistrationsByUserId(@PathVariable("userId") String userId) {
        try {
            int result = registrationService.deleteByUserId(userId);
            if (result > 0) {
                return Result.success("删除成功，共删除 " + result + " 条选课记录");
            } else {
                return Result.error("404", "未找到该用户的选课记录");
            }
        } catch (Exception e) {
            return Result.error("500", "删除失败: " + e.getMessage());
        }
    }

    /**
     * 统计课程选课人数
     */
    @Operation(summary = "统计课程选课人数", description = "统计指定课程的选课人数")
    @Parameters({
            @Parameter(name = "courseId", description = "课程ID", required = true)
    })
    @GetMapping("/course/{courseId}/count")
    public Result countByCourseId(@PathVariable("courseId") String courseId) {
        try {
            int count = registrationService.countByCourseId(courseId);
            return Result.success(count);
        } catch (Exception e) {
            return Result.error("500", "统计失败: " + e.getMessage());
        }
    }
}
