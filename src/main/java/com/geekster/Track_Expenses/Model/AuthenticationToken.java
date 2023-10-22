package com.geekster.Track_Expenses.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authId;
    private String tokenValue;

    private LocalDateTime creationTime;

    @OneToOne
    @JoinColumn(name = "fk-UserId")
    private User user;


    public AuthenticationToken(User existingUser) {
        this.user=existingUser;
        this.tokenValue= UUID.randomUUID().toString();
        this.setCreationTime(LocalDateTime.now());

    }
}
