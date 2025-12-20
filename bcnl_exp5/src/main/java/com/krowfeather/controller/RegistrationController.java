package com.krowfeather.controller;

import com.krowfeather.entity.Registration;
import com.krowfeather.entity.Result;
import com.krowfeather.service.RegistrationService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    @Resource
    private RegistrationService registrationService;

    // 新增选课记录
    @PostMapping
    public Result addRegistration(@RequestBody Registration registration) {
        try {
            registrationService.register(registration.getStudent(), registration.getCourse());
            return Result.success("选课记录添加成功");
        } catch (Exception e) {
            return Result.error("500", "选课记录添加失败: " + e.getMessage());
        }
    }

    // 根据ID查询选课记录
    @GetMapping("/{id}")
    public Result getRegistrationById(@PathVariable Long id) {
        Registration registration = registrationService.findById(id);
        if (registration != null) {
            return Result.success(registration);
        } else {
            return Result.error("404", "选课记录不存在");
        }
    }

    // 查询所有选课记录
    @GetMapping
    public Result getAllRegistrations() {
        List<Registration> registrations = registrationService.findAll();
        return Result.success(registrations);
    }

    // 删除选课记录
    @DeleteMapping("/{id}")
    public Result deleteRegistration(@PathVariable Long id) {
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
