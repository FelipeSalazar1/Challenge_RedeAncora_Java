package com.redeAncoraUsers.fiap.model;


import com.redeAncoraUsers.fiap.validators.UserPostData;
import com.redeAncoraUsers.fiap.validators.UserUpdateData;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "SEQ_USER", allocationSize = 1)
    private Long id;

    private String email;
    private String password;
    private Boolean activated;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false, unique = true)
    private Client client;

    public User(UserPostData data, Client client) {
        this.email = data.email();
        this.password = data.password();
        this.createdAt = LocalDateTime.now();
        this.activated = true;
        this.client = client;
    }

    public void updateInfo(@Valid UserUpdateData data) {
        if (data.email() != null) {
            this.email = data.email();
        }
        if (data.password() != null){
            this.password = data.password();
        }
    }

    public void registerUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

    public void deleteUser() {
        this.activated = false;
        this.deletedAt = LocalDateTime.now();
    }
}
