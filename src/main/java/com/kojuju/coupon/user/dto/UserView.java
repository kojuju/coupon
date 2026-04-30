package com.kojuju.coupon.user.dto;

import com.kojuju.coupon.user.entity.UserEntity;

import java.time.LocalDateTime;

public record UserView(
    Long id,
    String userName,
    String email,
    String status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

    public static UserView fromEntity(UserEntity entity) {
        return new UserView(
            entity.getId(),
            entity.getUserName(),
            entity.getEmail(),
            entity.getStatus(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }
}
