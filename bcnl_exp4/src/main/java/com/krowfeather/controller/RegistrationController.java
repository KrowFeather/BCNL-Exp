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

@Tag(name = "选课管理", description = "选课相关的API接口")
@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    @Resource
    private RegistrationService registrationService;

    @Operation(summary = "新增选课记录", description = "添加一个新的选课记录")
    @PostMapping
    public Result addRegistration(
            @Parameter(description = "选课信息")
            @RequestBody Registration registration) {
        try {
            registrationService.register(registration.getUser(), registration.getCourse());
            return Result.success("选课记录添加成功");
        } catch (Exception e) {
            return Result.error("500", "选课记录添加失败: " + e.getMessage());
        }
    }

    @Operation(summary = "根据ID查询选课记录", description = "根据选课记录ID查询详细信息")
    @Parameters({
            @Parameter(name = "id", description = "选课记录ID", required = true)
    })
    @GetMapping("/{id}")
    public Result getRegistrationById(
            @PathVariable Long id) {
        Registration registration = registrationService.findById(id);
        if (registration != null) {
            return Result.success(registration);
        } else {
            return Result.error("404", "选课记录不存在");
        }
    }

    @Operation(summary = "查询所有选课记录", description = "获取所有选课记录的列表信息")
    @GetMapping
    public Result getAllRegistrations() {
        List<Registration> registrations = registrationService.findAll();
        return Result.success(registrations);
    }

    @Operation(summary = "删除选课记录", description = "根据选课记录ID删除选课记录")
    @Parameters({
            @Parameter(name = "id", description = "选课记录ID", required = true)
    })
    @DeleteMapping("/{id}")
    public Result deleteRegistration(
            @PathVariable Long id) {
        try {
            Registration registration = new Registration();
            registration.setId(id);
            registrationService.delete(registration);
            return Result.success("选课记录删除成功");
        } catch (Exception e) {
            return Result.error("500", "选课记录删除失败: " + e.getMessage());
        }
    }
}
