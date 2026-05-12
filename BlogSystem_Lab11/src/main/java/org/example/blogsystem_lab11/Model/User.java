package org.example.blogsystem_lab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor @Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotEmpty(message = "Username cannot be empty")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;

    @Email @NotEmpty(message = "Email cannot be empty")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;

    @Column(columnDefinition = "date", updatable = false)
    private LocalDate registrationDate = LocalDate.now();
}