package com.redeAncoraUsers.fiap.validators;

import com.redeAncoraUsers.fiap.model.User;

import java.time.LocalDateTime;

public record UserGetData(Long id, String email, String password, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt, Long PersonId) {
    public UserGetData(User user) {
        this(user.getId(), user.getEmail(), user.getPassword(), user.getCreatedAt(), user.getUpdatedAt(), user.getDeletedAt(), user.getPerson().getId());
    }
}
