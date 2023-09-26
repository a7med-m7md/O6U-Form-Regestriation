package com.o6u.o6uform.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@NoArgsConstructor
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotEmpty
    private String nameAr;
    @NotNull
    @NotEmpty
    private String nameEn;
    @Column(unique = true)
    @NotNull
    @NotEmpty
    private String nationalId;
    @Column(unique = true)
    @NotNull
    @NotEmpty
    private String email;
    @Length(min = 11, max = 11, message = "Mobile number must be exactly 11 digits long")
    @Column(unique = true)
    private String mobile;
    @NotNull
    @NotEmpty
    private String bundle;
    @NotNull
    @NotEmpty
    private String groupp;

    private ZonedDateTime eventTime;

    @PrePersist
    public void setEventTime(){
        eventTime = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Africa/Cairo"));
        System.out.println(eventTime);
    }
}
