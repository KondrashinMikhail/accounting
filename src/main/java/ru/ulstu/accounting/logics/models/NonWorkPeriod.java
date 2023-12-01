package ru.ulstu.accounting.logics.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NonWorkPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long workerId;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    @Enumerated(EnumType.STRING)
    private NonWorkingReason reason;
    private Double payment;
}
