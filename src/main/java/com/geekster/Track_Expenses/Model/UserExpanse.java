package com.geekster.Track_Expenses.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserExpanse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String title;
    private String description;
    private Long prise;
    private LocalDateTime dateTime;


    @ManyToOne
    @JoinColumn(name = "fk_UserId")
    private User user;

    public UserExpanse(UserExpanse userExpanse) {
        this.title=userExpanse.getTitle();
        this.user=userExpanse.user;
        this.prise=userExpanse.prise;
        this.description=userExpanse.description;
        this.dateTime=LocalDateTime.now();
    }
}
