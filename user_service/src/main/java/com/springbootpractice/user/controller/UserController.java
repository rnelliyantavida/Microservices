package com.springbootpractice.user.controller;

import com.springbootpractice.user.entity.User;
import com.springbootpractice.user.service.UserService;
import com.springbootpractice.user.vo.Department;
import com.springbootpractice.user.vo.ResponseTemplateVO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private static final String USER_SERVICE = "userService";
    @Autowired
    private UserService userService;
    @PostMapping("/")
    public User saveUser(@RequestBody User user){
        log.info("inside of saveUser method in UserController");
        return userService.saveUser(user);
    }
    @CircuitBreaker(name = USER_SERVICE, fallbackMethod = "departmentServiceFallBack")
    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId){
        log.info("inside of getUserWithDepartment method in UserController");
        return userService.getUserWithDepartment(userId);
    }
    private ResponseTemplateVO departmentServiceFallBack(Throwable t){
        System.out.println("Department Service is Down");
        return null;
    }
}
