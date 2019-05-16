package com.gmail.model;

import com.gmail.annotations.InjectRandomInt;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Created by Space on 09.05.2019.
 */
@Data
@Setter
@Getter
@Entity
@Component
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name = "bithday_date")
    private LocalDate birthdayDate;

    @Column(name = "random_number")
    @InjectRandomInt(min = 20, max = 30)
    private int random;

    @Column
    private String email;
}
