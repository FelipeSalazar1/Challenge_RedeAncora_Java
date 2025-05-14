package br.com.redeAncora.System.validators;

import br.com.redeAncora.System.model.User;

import java.time.LocalDateTime;

public record UserGetData(Long id, String email, String password, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
    public UserGetData(User user) {
        this(user.getId(), user.getEmail(), user.getPassword(), user.getCreatedAt(), user.getUpdatedAt(), user.getDeletedAt());
    }
}
