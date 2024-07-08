package com.example.Library.Entities;

import java.util.*;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Fine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date imposedDate;
    private String description;
    private double Amount;

    @ManyToOne()
    @JoinColumn(name = "studentId", referencedColumnName = "student_id")
    Student student;

}
