package com.kojuju.coupon.user.controller;

import com.kojuju.coupon.common.api.ApiResponse;
import com.kojuju.coupon.user.dto.CreateUserRequest;
import com.kojuju.coupon.user.dto.UserView;
import com.kojuju.coupon.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ApiResponse<List<UserView>> listUsers() {
        List<UserView> result = userService.listUsers().stream()
            .map(UserView::fromEntity)
            .toList();
        return ApiResponse.success(result);
    }

    @GetMapping("/{id}")
    public ApiResponse<UserView> getUser(@PathVariable Long id) {
        return ApiResponse.success(UserView.fromEntity(userService.getUser(id)));
    }

    @PostMapping
    public ApiResponse<UserView> createUser(@Valid @RequestBody CreateUserRequest request) {
        return ApiResponse.success(UserView.fromEntity(userService.createUser(request)));
    }
}
