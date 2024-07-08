package com.example.Library.Entities;

import java.util.Date;
import com.example.Library.Enum.RequestType;
import com.example.Library.Enum.Request_status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long req_id;

    Date date;

    @Enumerated(value = EnumType.STRING)
    Request_status req_status;

    @Enumerated(value = EnumType.STRING)
    RequestType req_type;

    @OneToOne(mappedBy = "request")
    Transactions transactions;

    @ManyToOne()
    @JsonIgnoreProperties({ "request", "listOfFines", "user" })
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    Student student;

    @ManyToOne()
    @JoinColumn(name = "admin_id", referencedColumnName = "admin_id")
    @JsonIgnoreProperties("request")
    Admin admin;

    @ManyToOne()
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    @JsonIgnoreProperties("request")
    Book book;

}
