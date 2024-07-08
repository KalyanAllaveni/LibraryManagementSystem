package com.example.Library.Entities;

import com.example.Library.Enum.Transaction_status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long transaction_id;
    Transaction_status status;

    @OneToOne()
    @JoinColumn(name="request_id",referencedColumnName = "req_id")
    Request request;
}
