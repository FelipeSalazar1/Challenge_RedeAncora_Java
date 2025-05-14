package br.com.redeAncora.System.model;


import br.com.redeAncora.System.validators.UserPostData;
import br.com.redeAncora.System.validators.UserUpdateData;
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

    public User(UserPostData data) {
        this.email = data.email();
        this.password = data.password();
        this.createdAt = LocalDateTime.now();
        this.activated = true;
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
